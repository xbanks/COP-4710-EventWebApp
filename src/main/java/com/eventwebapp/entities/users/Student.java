package com.eventwebapp.entities.users;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "student")
public class Student {

    @Id
    Long id_student;

    @NotNull
    Long university_attending;

    @NotEmpty @Email
    String university_email;

    public Student() {
    }

    public Student(Long id_student, Long university_attending, String university_email) {
        this.id_student = id_student;
        this.university_attending = university_attending;
        this.university_email = university_email;
    }

    public Long getId_student() {
        return id_student;
    }

    public void setId_student(Long id_student) {
        this.id_student = id_student;
    }

    public Long getUniversity_attending() {
        return university_attending;
    }

    public void setUniversity_attending(Long university_attending) {
        this.university_attending = university_attending;
    }

    public String getUniversity_email() {
        return university_email;
    }

    public void setUniversity_email(String university_email) {
        this.university_email = university_email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id_student=" + id_student +
                ", university_attending=" + university_attending +
                ", university_email='" + university_email + '\'' +
                '}';
    }
}
