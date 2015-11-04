package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */

//@RepositoryRestResource(path = "/events")
public interface EventRepo extends JpaRepository<Event, Long> {
    Event findByName(String name);
}
