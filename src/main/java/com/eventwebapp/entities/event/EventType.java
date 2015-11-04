package com.eventwebapp.entities.event;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "event_type")
public class EventType implements Serializable {
    @Id
    Long id_event_type;

    String name;

    public EventType() {
    }

    public EventType(Long id_event_type, String name) {
        this.id_event_type = id_event_type;
        this.name = name;
    }

    public Long getId_event_type() {
        return id_event_type;
    }

    public void setId_event_type(Long id_event_type) {
        this.id_event_type = id_event_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "id_event_type=" + id_event_type +
                ", name='" + name + '\'' +
                '}';
    }
}
