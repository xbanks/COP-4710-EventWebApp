package com.eventwebapp.entities.event;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @GeneratedValue
    Long id_event;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Date needs to be in the future")
    LocalDate date;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    LocalTime time;

    @NotNull
    Long location;

    @NotEmpty
    @Size(min = 5, max = 500, message = "Must be between 5 and 500 characters")
    String description;

    @NotNull
    Long type;

    @NotNull
    Long host_rso;

    boolean sadmin_approved;
    boolean admin_approved;

    @NotNull
    @Size(min = 4, max = 45, message = "Name is not the correct length")
    String name;

    public Event() {
    }

    public Event(LocalDate date, LocalTime time, Long location, String description,
                 boolean admin_approved, boolean sadmin_approved,
                 Long type, Long host_rso, String name) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
        this.admin_approved = admin_approved;
        this.sadmin_approved = sadmin_approved;
        this.type = type;
        this.host_rso = host_rso;
        this.name = name;
    }

    public Long getId_event() {
        return id_event;
    }

    public void setId_event(Long id_event) {
        this.id_event = id_event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdmin_approved() {
        return admin_approved;
    }

    public void setAdmin_approved(boolean admin_approved) {
        this.admin_approved = admin_approved;
    }

    public boolean isSadmin_approved() {
        return sadmin_approved;
    }

    public void setSadmin_approved(boolean sadmin_approved) {
        this.sadmin_approved = sadmin_approved;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getHost_rso() {
        return host_rso;
    }

    public void setHost_rso(Long host_rso) {
        this.host_rso = host_rso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id_event=" + id_event +
                ", date=" + date +
                ", time=" + time +
                ", location=" + location +
                ", description='" + description + '\'' +
                ", admin_approved=" + admin_approved +
                ", sadmin_approved=" + sadmin_approved +
                ", type=" + type +
                ", host_rso=" + host_rso +
                ", name='" + name + '\'' +
                '}';
    }
}
