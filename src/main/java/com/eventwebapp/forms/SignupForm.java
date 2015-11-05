package com.eventwebapp.forms;

import com.eventwebapp.entities.users.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 11/5/15.
 */

@Service
public class SignupForm {

    @NotEmpty
    private String userFirstname;

    @NotEmpty
    private String userLastname;

    @NotEmpty
    private String userPassword;

    @NotEmpty
    @Email
    private String userEmail;

//    @Length(min = 10, max = 13)
    private String userPhone;

    @NotNull
    private Long universityId;

    @NotNull
    private Boolean applyForSAdmin;

    public User createUser(){
        if(this == null)
            return null;

        User user = new User();
        user.setEmail(this.userEmail);
        user.setFirstname(this.userFirstname);
        user.setLastname(this.userLastname);
        user.setPassword(this.userPassword);
        user.setPhone(this.userPhone);

        return user;
    }

    public SignupForm() {
    }

    public SignupForm(String userFirstname, String userLastname, String userPassword,
                      String userEmail, String userPhone, Long universityId, Boolean applyForSAdmin) {
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.universityId = universityId;
        this.applyForSAdmin = applyForSAdmin;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Boolean getApplyForSAdmin() {
        return applyForSAdmin;
    }

    public void setApplyForSAdmin(Boolean applyForSAdmin) {
        this.applyForSAdmin = applyForSAdmin;
    }
}
