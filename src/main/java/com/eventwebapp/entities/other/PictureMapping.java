package com.eventwebapp.entities.other;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "picture_mapping")
public class PictureMapping {

    @Id
    @GeneratedValue
    Long id_picture_mapping;

    @NotNull
    Long mapped_to_id;

    @NotNull
    Long entity_type;

    @NotNull
    Long picture;

    public PictureMapping() {
    }

    public PictureMapping(Long mapped_to_id, Long entity_type, Long picture) {
        this.mapped_to_id = mapped_to_id;
        this.entity_type = entity_type;
        this.picture = picture;
    }

    public Long getId_picture_mapping() {
        return id_picture_mapping;
    }

    public void setId_picture_mapping(Long id_picture_mapping) {
        this.id_picture_mapping = id_picture_mapping;
    }

    public Long getMapped_to_id() {
        return mapped_to_id;
    }

    public void setMapped_to_id(Long mapped_to_id) {
        this.mapped_to_id = mapped_to_id;
    }

    public Long getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(Long entity_type) {
        this.entity_type = entity_type;
    }

    public Long getPicture() {
        return picture;
    }

    public void setPicture(Long picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "PictureMapping{" +
                "id_picture_mapping=" + id_picture_mapping +
                ", mapped_to_id=" + mapped_to_id +
                ", entity_type=" + entity_type +
                ", picture=" + picture +
                '}';
    }
}
