package com.eventwebapp.validators;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.repositories.EventRepo;
import com.eventwebapp.repositories.EventTypeRepo;
import com.eventwebapp.repositories.LocationRepo;
import com.eventwebapp.repositories.RSORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Xavier on 11/3/2015.
 */
@Service
public class EventValidator implements ValidatorInterface<Event> {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    RSORepo rsoRepo;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    EventTypeRepo eventTypeRepo;

    @Override
    public boolean validate(Event entity) {
        boolean emptyName = (entity.getName() == null || entity.getName().trim() == "");
        boolean emptyDesc = (entity.getDescription() == null || entity.getDescription().trim() == "");
        boolean pastOrNullDate = (entity.getDate() == null || entity.getDate().isBefore(LocalDate.now()));
        boolean pastOrNullTime = (entity.getTime() == null || entity.getTime().isBefore(LocalTime.now()));
        boolean locationDNE = (entity.getLocation() == null || !(locationRepo.findOne(entity.getLocation()) == null));
        boolean rsoDNE = (entity.getHost_rso() == null || !rsoRepo.exists(entity.getHost_rso()));
        boolean typeDNE = (entity.getType() == null || !eventTypeRepo.exists(entity.getType()));

        return !(emptyName || emptyDesc || pastOrNullDate || pastOrNullTime
                || locationDNE || rsoDNE || typeDNE);

    }
}
