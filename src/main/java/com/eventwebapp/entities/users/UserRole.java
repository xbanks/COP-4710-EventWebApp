package com.eventwebapp.entities.users;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

    @Id
    @GeneratedValue
    private Long id_role;

    @NotNull
    private Long user;

    @NotNull
    @NotEmpty
    private String role;

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String SADMIN = "ROLE_SADMIN";
    public static final String STUDENT = "ROLE_STUDENT";

    public UserRole() {
    }

    public UserRole(Long user, String role) {
        this.user = user;
        this.role = role;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }
}
