package com.eventwebapp.entities.rso;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "rso_type")
public class RSOType implements Serializable {

    @Id
    Long id_rso_type;

    @NotEmpty
    String name;

    public RSOType() {
    }

    public RSOType(Long id_rso_type, String name) {
        this.id_rso_type = id_rso_type;
        this.name = name;
    }

    public Long getId_rso_type() {
        return id_rso_type;
    }

    public void setId_rso_type(Long id_rso_type) {
        this.id_rso_type = id_rso_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RSOType{" +
                "id_rso_type=" + id_rso_type +
                ", name='" + name + '\'' +
                '}';
    }
}
