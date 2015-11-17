package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.entities.event.EventType;
import com.eventwebapp.entities.event.EventsOnDay;
import com.eventwebapp.repositories.EventRepo;
import com.eventwebapp.repositories.EventTypeRepo;
import com.eventwebapp.repositories.LocationRepo;
import com.eventwebapp.repositories.RSORepo;
import com.eventwebapp.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Xavier on 11/2/2015.
 */

@Controller
@RequestMapping("/events")
public class EventRESTController {

    @Autowired
    private
    EventRepo eventRepo;

    @Autowired
    private
    LocationRepo locationRepo;

    @Autowired
    private
    EventTypeRepo eventTypeRepo;

    @Autowired
    private
    RSORepo rsoRepo;

    @RequestMapping("")
    public String events (Model model,
                          @RequestParam(value = "amount", required = false) Integer amount,
                          @RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "sortby", required = false) String sortby,
                          @RequestParam(value = "direction", required = false) String direction,
                          Principal principal){
        if(principal == null){
            List<Event> eventList = eventRepo.findAll()
                    .stream()
                    .filter(event -> event.getType() == EventType.PUBLIC)
                    .collect(Collectors.toList());
            model.addAttribute("list", eventRepo.findByType(EventType.PUBLIC));
        }
        else{
            amount = (amount == null) ? 10 : amount;
            page = (page == null) ? 0 : page;
            Sort.Direction direction1 = (direction == null) ? Sort.Direction.DESC : Sort.Direction.fromString(direction);
            sortby = (sortby == null) ? "date" : sortby;
            model.addAttribute("list", eventRepo.findAll(new PageRequest(page, amount, direction1, sortby)));
            System.out.println("PAGE:::::" + page);
            if(page > 0){model.addAttribute("prev_page", (page - 1));}
            model.addAttribute("next_page", page + 1);
        }
        model.addAttribute("listName", "Events");
        return "newlayout/events";
    }

    @RequestMapping("/{id}")
    public String eventByID(Model model, @PathVariable("id") Long id){
        model.addAttribute("events", eventRepo.findOne(id));

        return "test/events";
    }

    @RequestMapping("/thisweek")
    public String eventsThisWeek(Model model){

        List<EventsOnDay> eventsWeek = new ArrayList<>();
        eventRepo.findAll().stream().forEach(System.out::println);
        for (int i = 0; i < 7; i++) {
            Date date = DateConverter.convert(LocalDate.now().plusDays(i));
            List<Event> events =
                    eventRepo.findAll().stream()
                             .filter(event -> DateConverter.compare(event.getDate(), date))
                             .collect(Collectors.toList());
            if(!events.isEmpty())
            {
                eventsWeek.add(new EventsOnDay(events, date));
            }
        }

        model.addAttribute("list", eventsWeek);
        model.addAttribute("listName", "Events this Week");
        return "newlayout/events";
    }



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createEventPage(Model model, Event event){
        model.addAttribute("locations", locationRepo.findAll());
        model.addAttribute("eventTypes", eventTypeRepo.findAll());
        model.addAttribute("rsos", rsoRepo.findAll());

        return "newlayout/createEvent";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewEvent(Model model, @Valid Event event, BindingResult bindingResult){
        System.out.println("Inside post new");
        System.out.println(event);

        if(bindingResult.hasErrors()){
            model.addAttribute("locations", locationRepo.findAll());
            model.addAttribute("eventTypes", eventTypeRepo.findAll());
            model.addAttribute("rsos", rsoRepo.findAll());
            model.addAttribute("event", event);

            System.out.println("Invalid Event Created");
            return "newlayout/createEvent";
        }

        Event e = eventRepo.save(event);
        System.out.println(e);
        return String.format("redirect:/events/%d", e.getId_event());
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String eventCalendar(Model model){


        return "test/placeholder";
    }
}

