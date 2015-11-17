package com.eventwebapp.entities.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Created by Xavier on 11/2/2015.
 */
@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

    @Id
    private
    Long name_id;

    @GeneratedValue
    private
    Long id_user_type;

    private String name;

    public UserType() {
    }

    public UserType(Long name_id, String name) {
        this.name_id = name_id;
        this.name = name;
    }

    public Long getName_id() {
        return name_id;
    }

    public void setName_id(Long name_id) {
        this.name_id = name_id;
    }

    public Long getId_user_type() {
        return id_user_type;
    }

    public void setId_user_type(Long id_user_type) {
        this.id_user_type = id_user_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "name_id=" + name_id +
                ", id_user_type=" + id_user_type +
                ", name='" + name + '\'' +
                '}';
    }
}
