package com.eventwebapp.entities.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "super_admin")
public class SuperAdmin  {

    @Id
    private
    Long id_super_admin;

    public SuperAdmin() {
    }

    public SuperAdmin(Long id_super_admin) {
        this.id_super_admin = id_super_admin;
    }

    public Long getId_super_admin() {
        return id_super_admin;
    }

    public void setId_super_admin(Long id_super_admin) {
        this.id_super_admin = id_super_admin;
    }

    @Override
    public String toString() {
        return "SuperAdmin{" +
                "id_super_admin=" + id_super_admin +
                '}';
    }
}
