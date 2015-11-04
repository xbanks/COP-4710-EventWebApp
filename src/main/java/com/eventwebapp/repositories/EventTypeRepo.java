package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface EventTypeRepo extends JpaRepository<EventType, Long> {
}
