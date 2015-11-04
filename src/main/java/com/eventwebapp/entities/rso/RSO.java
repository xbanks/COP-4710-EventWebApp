package com.eventwebapp.entities.rso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "rso")
public class RSO implements Serializable {

    @Id
    @GeneratedValue
    Long id_rso;

    String name;

    Long type;

    Integer num_members;

    Long university;

    public RSO() {
    }

    public RSO(String name, Long type, Integer num_members, Long university) {
        this.name = name;
        this.type = type;
        this.num_members = num_members;
        this.university = university;
    }

    public Long getId_rso() {
        return id_rso;
    }

    public void setId_rso(Long id_rso) {
        this.id_rso = id_rso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getNum_members() {
        return num_members;
    }

    public void setNum_members(Integer num_members) {
        this.num_members = num_members;
    }

    public Long getUniversity() {
        return university;
    }

    public void setUniversity(Long university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "RSO{" +
                "id_rso=" + id_rso +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", num_members=" + num_members +
                ", university=" + university +
                '}';
    }
}
