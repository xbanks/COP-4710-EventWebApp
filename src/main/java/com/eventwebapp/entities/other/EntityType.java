package com.eventwebapp.entities.other;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "entity_type")
public class EntityType implements Serializable {

    @Id
    private
    Long id_entity_name;

    @NotEmpty
    private String entity_name;

    public EntityType() {
    }

    public EntityType(Long id_entity_name, String entity_name) {
        this.id_entity_name = id_entity_name;
        this.entity_name = entity_name;
    }

    public Long getId_entity_name() {
        return id_entity_name;
    }

    public void setId_entity_name(Long id_entity_name) {
        this.id_entity_name = id_entity_name;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    @Override
    public String toString() {
        return "EntityType{" +
                "id_entity_name=" + id_entity_name +
                ", entity_name='" + entity_name + '\'' +
                '}';
    }
}
