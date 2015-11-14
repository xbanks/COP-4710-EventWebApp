package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Xavier on 11/2/2015.
 */

//@RepositoryRestResource(path = "/events")
public interface EventRepo extends JpaRepository<Event, Long> {
    Event findByName(String name);
    List<Event> findByDate(Date date);
}
