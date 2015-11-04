package com.eventwebapp.controllers;

import com.eventwebapp.entities.users.Admin;
import com.eventwebapp.repositories.AdminRepo;
import com.eventwebapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Xavier on 11/4/2015.
 */

@Controller
@RequestMapping("/user/admin")
public class AdminRESTController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AdminRepo adminRepo;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAdmin(Model model){
        // maybe this should be done with the rso creation???

        return "placeholder";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNewAdmin(Model model, @Valid Admin admin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "placeholder";
        }

        if(!userRepo.exists(admin.getId_admin())){

            // TODO: send to user sign in page
            return "placeholder";
        }

        adminRepo.save(admin);

        // TODO: send back to dashboard?
        return "placeholder";
    }

    // TODO: admin rso ownership controls?

}
