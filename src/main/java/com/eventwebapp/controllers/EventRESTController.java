package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.*;
import com.eventwebapp.entities.other.Location;
import com.eventwebapp.forms.EventForm;
import com.eventwebapp.repositories.*;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Xavier on 11/2/2015.
 */

@Controller
@RequestMapping("/events")
public class EventRESTController {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private EventTypeRepo eventTypeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RSORepo rsoRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private EventRatingRepo eventRatingRepo;

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

//    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
//    public String createComment(Model model, Comment comment, Principal principal, @PathVariable("id") Long id){
//        if(principal == null){
//            return "test/placeholder";
//        }
//
//        if(comment == null || comment.getContent() == null || comment.getContent().trim() == ""){
//            model.addAttribute("message", "This comment is empty");
//            return "test/placeholder";
//        }
//
//        comment.setCommenter(userRepo.findByUsername(principal.getName()).getId_user());
//        comment.setCommenterName(principal.getName());
//        comment.setTimestamp(new Date());
//        comment.setEvent(id);
//        comment.setContent(comment.getContent().trim());
//        commentRepo.save(comment);
//
//        return String.format("redirect:/events/%d", id);
//    }

    @RequestMapping("/{id}")
    public String eventByID(Model model, @PathVariable("id") Long id,
                            Principal principal, Comment comment){
        Event event = eventRepo.findOne(id);

        // Make sure the event actually exists.
        // This should send them to a "This event does not exist" page
        if (event == null) {
            return "test/placeholder";
        }

        model.addAttribute("event", event);
        model.addAttribute("location", locationRepo.findOne(event.getLocation()));
        model.addAttribute("eventType", eventTypeRepo.findOne(event.getType()).getName());
//        List<Comment> comments = commentRepo.findByEvent(event.getId_event());
//        comments.sort(Comparator.comparing(Comment::getTimestamp));

//        model.addAttribute("comments", comments);
        int rating = 0;

        if(eventRatingRepo.findByEvent(event.getId_event()) != null){
            OptionalDouble rtng = eventRatingRepo.findByEvent(event.getId_event()).stream()
                        .mapToInt(EventRating::getRating)
                        .average();

            if(rtng.isPresent()){
                rating = (int) rtng.getAsDouble();
            }
        }
        if (principal != null){
            model.addAttribute("principal", principal);
        }

        model.addAttribute("rating", rating);

        return "newlayout/eventPage";
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
    public String createEventPage(Model model, EventForm eventForm, Principal principal){
        model.addAttribute("locations", locationRepo.findAll());
        model.addAttribute("eventTypes", eventTypeRepo.findAll());
        model.addAttribute("rsos", rsoRepo.findAll());

        return "newlayout/createEvent";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewEvent(Model model, @Valid EventForm eventForm, BindingResult bindingResult){
        System.out.println("Inside post new");
        System.out.println(eventForm);

        if(bindingResult.hasErrors()){
            model.addAttribute("locations", locationRepo.findAll());
            model.addAttribute("eventTypes", eventTypeRepo.findAll());
            model.addAttribute("rsos", rsoRepo.findAll());
            model.addAttribute("eventForm", eventForm);
            System.out.println(bindingResult);
            System.out.println("Invalid Event Created");
            return "newlayout/createEvent";
        }

        Event e = eventForm.buildEvent();
        Location location = locationRepo.findByPlaceId(eventForm.getPlaceId());
        if(location == null){
            location = locationRepo.save(eventForm.buildLocation());
        }
        e.setLocation(location.getId_location());
        eventRepo.save(e);

        System.out.println(e);
        return String.format("redirect:/events/%d", e.getId_event());
    }

//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String eventCalendar(Model model){
        return "newlayout/calendar";
    }
}

