package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Xavier on 11/3/2015.
 */
public @Controller
@RequestMapping("/api/event")
class EventAPI {

    @Autowired
    EventRepo eventRepo;

    @RequestMapping("")
    public ResponseEntity<List<Event>> allEvents(){
        return new ResponseEntity<List<Event>>(eventRepo.findAll(), HttpStatus.OK);
    }
}