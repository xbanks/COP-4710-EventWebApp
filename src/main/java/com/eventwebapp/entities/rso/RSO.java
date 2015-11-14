package com.eventwebapp.entities.rso;

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
@Table(name = "rso")
public class RSO implements Serializable {

    @Id
    @GeneratedValue
    private Long id_rso;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Long type;

    @NotEmpty
    private String description;

    @NotNull
    private Long num_members;

    @NotNull
    private Long university;

    public RSO() {
    }

    public RSO(String name, Long type, String description, Long num_members, Long university) {
        this.name = name;
        this.type = type;
        this.description = description;
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

    public Long getNum_members() {
        return num_members;
    }

    public void setNum_members(Long num_members) {
        this.num_members = num_members;
    }

    public Long getUniversity() {
        return university;
    }

    public void setUniversity(Long university) {
        this.university = university;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RSO{" +
                "id_rso=" + id_rso +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", num_members=" + num_members +
                ", university=" + university +
                '}';
    }
}
