package com.eventwebapp.entities.other;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue
    private
    Long id_location;

    @NotEmpty
    private String placeId;

    @NotEmpty @Size(min = 2)
    String name;

    @NotNull
    private
    Float latitude;

    @NotNull
    private
    Float longitude;

    public Location() {
    }

    public Location(String name, String placeId, Float latitude, Float longitude) {
        this.name = name;
        this.placeId = placeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId_location() {
        return id_location;
    }

    public void setId_location(Long id_location) {
        this.id_location = id_location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id_location=" + id_location +
                ", placeId='" + placeId + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
