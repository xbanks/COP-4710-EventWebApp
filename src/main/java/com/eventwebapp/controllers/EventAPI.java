package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Comment;
import com.eventwebapp.entities.event.Event;
import com.eventwebapp.repositories.CommentRepo;
import com.eventwebapp.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Xavier on 11/3/2015.
 */
public @Controller
@RequestMapping("/api/events")
class EventAPI {

    @Autowired
    private
    EventRepo eventRepo;

    @Autowired
    private
    CommentRepo commentRepo;

    @RequestMapping("")
    public ResponseEntity<List<Event>> allEvents(@RequestParam(value = "approval", required = false) Boolean approved){
        Stream<Event> eventStream =
                eventRepo.findAll()
                         .stream()
                         .sorted(Comparator.comparing(Event::getId_event));

        if(approved != null) {
            eventStream = approved ? eventStream.filter(Event::isAdmin_approved) :
                                     eventStream.filter(event -> !event.isAdmin_approved());
        }
        System.out.println("approved: " + approved);

        return ResponseEntity.ok(eventStream.collect(Collectors.toList()));
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Event> eventById(@PathVariable("id") Long id){
        Event event = eventRepo.findOne(id);
        return ResponseEntity.ok(event);
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.GET)
    ResponseEntity<?> getComments(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentRepo.findByEvent(id));
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    ResponseEntity<?> createComment(@PathVariable("id") Long id, @RequestParam("content") String content){
        try {
            commentRepo.save(new Comment(content, 1L, id));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}