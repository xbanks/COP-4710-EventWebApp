package com.eventwebapp.entities.users;

import org.hibernate.validator.constraints.Email;
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
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    Long id_user;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Names are capped at 30 characters")
    String firstname;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Names are capped at 30 characters")
    String lastname;

    @NotNull
    String password;

    @NotEmpty @Email
    String email;

    @Size(min = 10, message = "Phone numbers must be at least 10 characters long")
    String phone;

    public User() {
    }

    public User(String firstname, String lastname, String password, String email, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
