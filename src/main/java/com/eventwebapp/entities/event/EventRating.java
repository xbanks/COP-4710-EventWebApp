package com.eventwebapp.entities.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "event_rating")
public class EventRating implements Serializable{

    @Id
    @GeneratedValue
    private Long id_rating;

    private Long event;

    private Long reviewer;

    @Max(value = 5)
    private
    Integer rating;

    public EventRating() {
    }

    public EventRating(Long event, Long reviewer, Integer rating) {
        this.event = event;
        this.reviewer = reviewer;
        this.rating = rating;
    }

    public Long getId_rating() {
        return id_rating;
    }

    public void setId_rating(Long id_rating) {
        this.id_rating = id_rating;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long getReviewer() {
        return reviewer;
    }

    public void setReviewer(Long reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "EventRating{" +
                "event=" + event +
                ", reviewer=" + reviewer +
                ", rating=" + rating +
                '}';
    }
}
