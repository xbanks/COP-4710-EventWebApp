package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.entities.event.EventsOnDay;
import com.eventwebapp.repositories.EventRepo;
import com.eventwebapp.repositories.EventTypeRepo;
import com.eventwebapp.repositories.LocationRepo;
import com.eventwebapp.repositories.RSORepo;
import com.eventwebapp.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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
    EventRepo eventRepo;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    EventTypeRepo eventTypeRepo;

    @Autowired
    RSORepo rsoRepo;

    @RequestMapping("")
    public String events (Model model){
        model.addAttribute("events", eventRepo.findAll(new PageRequest(0, 5, Sort.Direction.DESC, "date")));
        return "main/events";
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

        model.addAttribute("week", eventsWeek);

        return "main/weeklyView";
    }



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createEventPage(Model model){
        model.addAttribute("locations", locationRepo.findAll());
        model.addAttribute("eventTypes", eventTypeRepo.findAll());
        model.addAttribute("rsos", rsoRepo.findAll());
        model.addAttribute("event", new Event());

        return "main/eventForm";
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
            return "main/eventForm";
        }

        Event e = eventRepo.save(event);
        System.out.println(e);
        return String.format("redirect:/events/%d", e.getId_event());
    }
}

