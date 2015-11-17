package com.eventwebapp.entities.rso;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by xavier on 11/6/15.
 */

@Entity
@Table(name = "rso_member")
public class RsoMember implements Serializable{

    @Id
    private
    Long affiliated_rso;

    @NotNull
    private
    Long member;

    public RsoMember(){

    }

    public RsoMember(Long affiliated_rso, Long member) {
        this.affiliated_rso = affiliated_rso;
        this.member = member;
    }

    public Long getAffiliated_rso() {
        return affiliated_rso;
    }

    public void setAffiliated_rso(Long affiliated_rso) {
        this.affiliated_rso = affiliated_rso;
    }

    public Long getMember() {
        return member;
    }

    public void setMember(Long member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "RsoMember{" +
                "affiliated_rso=" + affiliated_rso +
                ", member=" + member +
                '}';
    }
}
