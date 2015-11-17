package com.eventwebapp.entities.event;

import org.hibernate.validator.constraints.NotEmpty;

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
    public static final Long PUBLIC = 0L;
    public static final Long PRIVATE = 0L;
    public static final Long RSO = 0L;

    @Id
    private
    Long id_event_type;

    @NotEmpty
    private String name;

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
