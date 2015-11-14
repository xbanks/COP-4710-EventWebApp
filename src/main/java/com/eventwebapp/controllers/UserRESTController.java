package com.eventwebapp.controllers;

import com.eventwebapp.entities.users.User;
import com.eventwebapp.forms.SignupForm;
import com.eventwebapp.repositories.UniversityRepo;
import com.eventwebapp.repositories.UserRepo;
import com.eventwebapp.security.CustomUserDetails;
import com.eventwebapp.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Xavier on 11/3/2015.
 */

@Controller
public class UserRESTController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UniversityRepo universityRepo;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model, SignupForm signupForm){
        model.addAttribute("universities", universityRepo.findAll());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(Model model, @Valid SignupForm signupForm, BindingResult bindingResult){

        // Shows if there were any validation errors with the form
        boolean errorExists = false;
        if(bindingResult.hasErrors()){
            System.out.println("Form Screwed");
            errorExists = true;
        }
        if(userRepo.findByEmail(signupForm.getEmail()) != null){
            bindingResult.rejectValue("userEmail", "error.signupForm", "This email is already registered");
            System.out.println("Email taken");
        }
        if(errorExists){
            model.addAttribute("universities", universityRepo.findAll());
            return "register";
        }

        // TODO: Use this to login after saving
        User user = signupForm.createUser();
        User u = userRepo.save(user);
        System.out.println("Successfully created user");

        // TODO: Maybe send to the student creation page?
        model.addAttribute("message", String.format("Successfully created user: %s", u));
        return "test/placeholder";
    }

    // Used for post request using JSON
    @RequestMapping(value = "api/new/", method = RequestMethod.POST)
    public ResponseEntity<User> CreateNewUser (@RequestBody User user){
        if(userRepo.findByEmail(user.getEmail()) != null){
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        }

        User new_user = userRepo.save(user);

        return new ResponseEntity<User>(new_user, HttpStatus.OK);
    }


    // THIS IS JUST FOR TESTING
    @RequestMapping(value = "/something", method = RequestMethod.GET)
    public String user(Model model, Principal principal){
        if(principal != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
            model.addAttribute("message", String.format("Hello from: user %s", customUserDetails));
        }
        return "test/placeholder";
    }
}
