package com.eventwebapp.entities.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
//@Table(name = "admin")
public class Admin  {

    @Id
    Long id_admin;

    @NotNull
    Long owned_rso;

    public Admin() {
    }

    public Admin(Long id_admin, Long owned_rso) {
        this.id_admin = id_admin;
        this.owned_rso = owned_rso;
    }

    public Long getId_admin() {
        return id_admin;
    }

    public void setId_admin(Long id_admin) {
        this.id_admin = id_admin;
    }

    public Long getOwned_rso() {
        return owned_rso;
    }

    public void setOwned_rso(Long owned_rso) {
        this.owned_rso = owned_rso;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id_admin=" + id_admin +
                ", owned_rso=" + owned_rso +
                '}';
    }
}
