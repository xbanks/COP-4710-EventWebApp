package com.eventwebapp.entities.other;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "picture")
public class Picture implements Serializable {

    @Id
    @GeneratedValue
    Long id_picture;

    @NotEmpty
    String path;

    public Picture() {
    }

    public Picture(String path) {
        this.path = path;
    }

    public Long getId_picture() {
        return id_picture;
    }

    public void setId_picture(Long id_picture) {
        this.id_picture = id_picture;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id_picture=" + id_picture +
                ", path=" + path +
                '}';
    }
}
