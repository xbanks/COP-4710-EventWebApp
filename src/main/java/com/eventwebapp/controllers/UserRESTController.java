package com.eventwebapp.controllers;

import com.eventwebapp.entities.users.User;
import com.eventwebapp.forms.SignupForm;
import com.eventwebapp.repositories.UniversityRepo;
import com.eventwebapp.repositories.UserRepo;
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

/**
 * Created by Xavier on 11/3/2015.
 */

@Controller
@RequestMapping("/user")
public class UserRESTController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UniversityRepo universityRepo;

    // Serves the model form
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String NewUser(Model model, SignupForm signupForm){
        model.addAttribute("universities", universityRepo.findAll());
//        model.addAttribute("form", form);
        return "newuser";
    }

    // THIS IS JUST FOR TESTING
    @RequestMapping(value = "/something", method = RequestMethod.GET)
    public String user(User user, Model model){
        if(user.equals(null)){
            return "newuser";
        }
        System.out.println(user);
        model.addAttribute("message", String.format("Hello from: user %s", user));
        return "placeholder";
    }

    // This would be used from a form
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNewUser(Model model, @Valid SignupForm signupForm, BindingResult bindingResult){

        // Shows if there were any validation errors with the form
        boolean errorExists = false;
        if(bindingResult.hasErrors()){
            System.out.println("Form Screwed");
            errorExists = true;
        }
        if(userRepo.findByEmail(signupForm.getUserEmail()) != null){
            bindingResult.rejectValue("userEmail", "error.signupForm", "This email is already registered");
            System.out.println("Email taken");
        }
        if(errorExists){
            model.addAttribute("universities", universityRepo.findAll());
            return "newuser";
        }

        // TODO: Use this to login after saving
        User user = signupForm.createUser();
        User u = userRepo.save(user);
        System.out.println("Successfully created user");

        // TODO: Maybe send to the student creation page?
        model.addAttribute("message", String.format("Successfully created user: %s", u));
        return "placeholder";
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
}
