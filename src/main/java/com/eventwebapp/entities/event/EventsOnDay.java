package com.eventwebapp.entities.event;

import java.util.Date;
import java.util.List;

/**
 * Created by xavier on 11/13/15.
 */
public class EventsOnDay {

    private List<Event> events;
    private Date date;

    public EventsOnDay() {
    }

    public EventsOnDay(List<Event> events, Date date) {
        this.events = events;
        this.date = date;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EventsOnDay{" +
                "events=" + events +
                ", date=" + date +
                '}';
    }
}
