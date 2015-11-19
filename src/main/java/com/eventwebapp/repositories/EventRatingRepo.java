package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.EventRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface EventRatingRepo extends JpaRepository<EventRating, Long> {

    List<EventRating> findByEvent(Long event);
}
