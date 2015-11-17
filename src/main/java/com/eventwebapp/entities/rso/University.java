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
@Table(name = "university")
public class University implements Serializable {

    @Id
    @GeneratedValue
    private
    Long id_university;

    @NotEmpty
    String name;

    @NotEmpty @Size(max = 500)
    String description;

    @NotNull
    private
    Long uni_location;

    @NotNull
    private
    Long num_students;

    @NotEmpty @Size(max = 10)
    String short_name;

    public University() {
    }

    public University(String name, String description, Long uni_location,
                      Long num_students, String short_name) {
        this.name = name;
        this.description = description;
        this.uni_location = uni_location;
        this.num_students = num_students;
        this.short_name = short_name;
    }

    public Long getId_university() {
        return id_university;
    }

    public void setId_university(Long id_university) {
        this.id_university = id_university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUni_location() {
        return uni_location;
    }

    public void setUni_location(Long uni_location) {
        this.uni_location = uni_location;
    }

    public Long getNum_students() {
        return num_students;
    }

    public void setNum_students(Long num_students) {
        this.num_students = num_students;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    @Override
    public String toString() {
        return "University{" +
                "id_university=" + id_university +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uni_location=" + uni_location +
                ", num_students=" + num_students +
                ", short_name='" + short_name + '\'' +
                '}';
    }
}
