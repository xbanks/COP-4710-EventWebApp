package com.eventwebapp.forms;

import com.eventwebapp.entities.rso.RSO;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 11/5/15.
 */
public class RsoForm extends RSO{

    private static final int requiredMembers = 5;

    @NotEmpty
    @Email
    private String userEmail1;

    @NotEmpty
    @Email
    private String userEmail2;

    @NotEmpty
    @Email
    private String userEmail3;

    @NotEmpty
    @Email
    private String userEmail4;

    @NotEmpty
    @Email
    private String userEmail5;

    @NotNull
    private Long rsoAdminId;

    public RsoForm() {
    }

    public RsoForm(String name, Long type, Integer num_members, Long university,
                   String userEmail1, String userEmail2, String userEmail3,
                   String userEmail4, String userEmail5, Long rsoAdminId) {
        super(name, type, num_members, university);
        this.userEmail1 = userEmail1;
        this.userEmail2 = userEmail2;
        this.userEmail3 = userEmail3;
        this.userEmail4 = userEmail4;
        this.userEmail5 = userEmail5;
        this.rsoAdminId = rsoAdminId;
    }

    // Make sure all five emails have matching domains.
    public boolean matchingDomains(){
        return this.userEmail1.split("@")[1] == this.userEmail2.split("@")[1] &&
                this.userEmail2.split("@")[1] == this.userEmail3.split("@")[1] &&
                this.userEmail3.split("@")[1] == this.userEmail4.split("@")[1] &&
                this.userEmail4.split("@")[1] == this.userEmail5.split("@")[1];

    }

    public RSO createRSO(){
        if(this.matchingDomains()){
            RSO rso = new RSO(this.getName(), this.getType(), this.getNum_members(), this.getUniversity());
            return rso;
        }
        return null;
    }

    public List<String> foundingMembers(){
        List<String> list = new ArrayList<String>();
        list.add(this.getUserEmail1());
        list.add(this.getUserEmail2());
        list.add(this.getUserEmail3());
        list.add(this.getUserEmail4());
        list.add(this.getUserEmail5());

        return list;
    }

    public String getUserEmail1() {
        return userEmail1;
    }

    public void setUserEmail1(String userEmail1) {
        this.userEmail1 = userEmail1;
    }

    public String getUserEmail2() {
        return userEmail2;
    }

    public void setUserEmail2(String userEmail2) {
        this.userEmail2 = userEmail2;
    }

    public String getUserEmail3() {
        return userEmail3;
    }

    public void setUserEmail3(String userEmail3) {
        this.userEmail3 = userEmail3;
    }

    public String getUserEmail4() {
        return userEmail4;
    }

    public void setUserEmail4(String userEmail4) {
        this.userEmail4 = userEmail4;
    }

    public String getUserEmail5() {
        return userEmail5;
    }

    public void setUserEmail5(String userEmail5) {
        this.userEmail5 = userEmail5;
    }

    public Long getRsoAdminId() {
        return rsoAdminId;
    }

    public void setRsoAdminId(Long rsoAdminId) {
        this.rsoAdminId = rsoAdminId;
    }
}
