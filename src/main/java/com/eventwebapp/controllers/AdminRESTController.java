package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.entities.rso.RSO;
import com.eventwebapp.entities.rso.University;
import com.eventwebapp.entities.users.Admin;
import com.eventwebapp.entities.users.User;
import com.eventwebapp.repositories.AdminRepo;
import com.eventwebapp.repositories.EventRepo;
import com.eventwebapp.repositories.UserRepo;
import com.eventwebapp.security.CustomUserDetails;
import com.eventwebapp.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Xavier on 11/4/2015.
 */

@Controller
@RequestMapping("/admin")
public class AdminRESTController {

    @Autowired
    private
    UserRepo userRepo;

    @Autowired
    private
    AdminRepo adminRepo;

    @Autowired
    private
    EventRepo eventRepo;

    @Autowired
    private
    CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "")
    public String adminDashboard(Model model){
        List<Event> pendingEvents =
                eventRepo.findAll().stream()
                         .filter(event -> !event.isAdmin_approved())
                         .collect(Collectors.toList());

        model.addAttribute("pendingEvents", pendingEvents);
        return "newlayout/adminEvents";
    }

    @RequestMapping(value = "/pendingevents")
    public String unapproved(Model model){
        List<Event> pendingEvents =
                eventRepo.findAll().stream()
                        .filter(event -> !event.isAdmin_approved())
                        .collect(Collectors.toList());

        model.addAttribute("pendingEvents", pendingEvents);
        return "newlayout/adminEvents";
    }

    @RequestMapping(value = "users")
    public String users(Model model){
        // TODO: 11/12/15 Change this back to !user.isEnabled()
        List<CustomUserDetails> lockedUsers =
                userRepo.findAll().stream()
                        .filter(User::isEnabled)
                        .map(User::getEmail)
                        .map(userDetailsService::loadUserByUsername)  // TODO: 11/12/15 change the email/username thing
                        .map(userDetails -> (CustomUserDetails)userDetails)
                        .collect(Collectors.toList());
        model.addAttribute("users", lockedUsers);
        return "newlayout/adminUsers";
    }

    @RequestMapping(value = "/create/university", method = RequestMethod.GET)
    public String createUni(Model model, University university){
        return "newlayout/createUni";
    }

    @RequestMapping(value = "/create/university", method = RequestMethod.POST)
    public String createNewUni(Model model, @Valid University university, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "newlayout/createUni";
        }

        return "redirect:/admin";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAdmin(Model model){
        // maybe this should be done with the rso creation???

        return "test/placeholder";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewAdmin(Model model, @Valid Admin admin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "test/placeholder";
        }

        if(!userRepo.exists(admin.getId_admin())){

            // TODO: send to user sign in page
            return "test/placeholder";
        }

        adminRepo.save(admin);

        // TODO: send back to dashboard?
        return "test/placeholder";
    }

    // TODO: admin rso ownership controls?

}
