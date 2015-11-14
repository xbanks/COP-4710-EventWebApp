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
    private String adminEmail;

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

    public RsoForm() {
    }

    public RsoForm(String name, Long type, String description, Long num_members, Long university,
                   String userEmail1, String userEmail2, String userEmail3,
                   String userEmail4, String adminEmail) {
        super(name, type, description, num_members, university);
        this.userEmail1 = userEmail1.toLowerCase();
        this.userEmail2 = userEmail2.toLowerCase();
        this.userEmail3 = userEmail3.toLowerCase();
        this.userEmail4 = userEmail4.toLowerCase();
        this.adminEmail = adminEmail.toLowerCase();
    }


    // TODO: 11/13/15 make sure the emails dont actually fully match though
    // Make sure all five emails have matching domains.
    public boolean matchingDomains(){
        boolean matching = this.userEmail1.split("@")[1].equals(this.userEmail2.split("@")[1]) &&
                this.userEmail2.split("@")[1].equals(this.userEmail3.split("@")[1]) &&
                this.userEmail3.split("@")[1].equals(this.userEmail4.split("@")[1]) &&
                this.userEmail4.split("@")[1].equals(this.adminEmail.split("@")[1]);

        System.out.println(this.foundingMembers().toString());

        System.out.println("Emails Match: " + matching);

        return matching;
    }

    public RSO createRSO(){
        if(this.matchingDomains()){
            RSO rso = new RSO(this.getName(), this.getType(), this.getDescription(), this.getNum_members(), this.getUniversity());
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
        list.add(this.getAdminEmail());

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

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
