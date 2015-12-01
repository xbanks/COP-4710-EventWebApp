package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Xavier on 11/2/2015.
 */

//@RepositoryRestResource(path = "/events")
public interface EventRepo extends JpaRepository<Event, Long> {
    Event findByName(String name);
    List<Event> findByDate(Date date);

    @Query("SELECT e FROM Event e WHERE e.host_rso=:host_rso")
    List<Event> findByHost_Rso(@Param("host_rso") Long host_rso);

    @Query("SELECT e FROM Event e WHERE e.type=:type")
    List<Event> findByType(@Param("type") Long type);

    @Query("SELECT e FROM Event e, EventType et WHERE e.type=et.id_event_type AND et.name='PUBLIC'")
    List<Event> findPublicEvents();

    @Query("SELECT e FROM Event e, EventType et WHERE e.type=et.id_event_type AND et.name='PRIVATE'")
    List<Event> findPrivateEvents();

    @Query("SELECT e FROM Event e, EventType et WHERE e.type=et.id_event_type AND et.name='RSO'")
    List<Event> findRsoEvents();
}
