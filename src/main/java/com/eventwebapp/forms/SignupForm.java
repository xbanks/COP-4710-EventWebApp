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
public class SignupForm extends User{

    @NotNull
    private Long universityId;

    @NotNull
    private Boolean applyForSAdmin;

    public User createUser(){
        if(this == null)
            return null;

        User user = (User) this;
        return user;
    }

    public SignupForm() {
    }

    public SignupForm(String firstname, String lastname, String password, String email,
                      String phone, Long universityId, Boolean applyForSAdmin) {
        super(firstname, lastname, password, email, phone);
        this.universityId = universityId;
        this.applyForSAdmin = applyForSAdmin;
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
