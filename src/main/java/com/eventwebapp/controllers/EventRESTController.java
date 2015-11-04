package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.repositories.EventRepo;
import com.eventwebapp.repositories.EventTypeRepo;
import com.eventwebapp.repositories.LocationRepo;
import com.eventwebapp.repositories.RSORepo;
import com.eventwebapp.validators.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @Autowired
    EventValidator eventValidator;

    @RequestMapping("")
    public String events (Model model){
        model.addAttribute("events", eventRepo.findAll());

        return "events";
    }

    @RequestMapping("/{id}")
    public String eventByID(Model model, @PathVariable("id") Long id){
        model.addAttribute("events", eventRepo.findOne(id));

        return "events";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newEventPage(Model model){
        model.addAttribute("locations", locationRepo.findAll());
        model.addAttribute("eventTypes", eventTypeRepo.findAll());
        model.addAttribute("rsos", rsoRepo.findAll());
        model.addAttribute("event", new Event());

        return "newevent";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNewEvent(Model model, @Valid Event event, BindingResult bindingResult){
        System.out.println("Inside post new");
        System.out.println(event);

        if(bindingResult.hasErrors()){
            model.addAttribute("locations", locationRepo.findAll());
            model.addAttribute("eventTypes", eventTypeRepo.findAll());
            model.addAttribute("rsos", rsoRepo.findAll());
            model.addAttribute("event", event);

            System.out.println("Invalid Event Created");
            return "newevent";
        }
//        if(!eventValidator.validate(event)) {
//            model.addAttribute("locations", locationRepo.findAll());
//            model.addAttribute("eventTypes", eventTypeRepo.findAll());
//            model.addAttribute("rsos", rsoRepo.findAll());
//            model.addAttribute("event", event);
//
//            System.out.println("Invalid Event Created");
//            return "newevent";
//        }

        Event e = eventRepo.save(event);
        System.out.println(e);
        return String.format("redirect:/events/%d", + e.getId_event());
    }
}

