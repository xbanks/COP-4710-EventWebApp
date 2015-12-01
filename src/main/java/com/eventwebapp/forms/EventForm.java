package com.eventwebapp.forms;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.entities.other.Location;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by xavier on 11/29/15.
 */
public class EventForm extends Event {

    @NotEmpty
    private String placeId;

    @NotEmpty
    private String placeName;

    @NotNull
    private Float placeLat;

    @NotNull
    private Float placeLon;

    public EventForm() {
    }

    public EventForm(Date date, LocalTime time, Long location, String description,
                     boolean admin_approved, boolean sadmin_approved, Long type, Long host_rso,
                     String name, String contactName, String contactEmail, String contactPhone,
                     String placeId, String placeName, Float placeLat, Float placeLon) {
        super(date, time, location, description, admin_approved, sadmin_approved, type, host_rso, name, contactName, contactEmail, contactPhone);
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeLat = placeLat;
        this.placeLon = placeLon;
    }

    public Event buildEvent(){
        Event event = new Event();
        event.setDate(this.getDate());
        event.setTime(this.getTime());
        event.setLocation(this.getLocation());
        event.setDescription(this.getDescription());
        event.setAdmin_approved(this.isAdmin_approved());
        event.setSadmin_approved(this.isSadmin_approved());
        event.setType(this.getType());
        event.setHost_rso(this.getHost_rso());
        event.setName(this.getName());
        event.setContactName(this.getContactName());
        event.setContactEmail(this.getContactEmail());
        event.setContactPhone(this.getContactPhone());

        return event;
    }

    public Location buildLocation(){
        Location location = new Location();
        location.setPlaceId(this.placeId);
        location.setName(this.placeName);
        location.setLatitude(this.getPlaceLat());
        location.setLongitude(this.getPlaceLon());

        return location;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Float getPlaceLat() {
        return placeLat;
    }

    public void setPlaceLat(Float placeLat) {
        this.placeLat = placeLat;
    }

    public Float getPlaceLon() {
        return placeLon;
    }

    public void setPlaceLon(Float placeLon) {
        this.placeLon = placeLon;
    }

    @Override
    public String toString() {
        return "EventForm{" +
                "placeId='" + placeId + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeLat=" + placeLat +
                ", placeLon=" + placeLon +
                '}';
    }
}
