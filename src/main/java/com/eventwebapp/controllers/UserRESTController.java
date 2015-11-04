package com.eventwebapp.controllers;

import com.eventwebapp.entities.users.User;
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

    // Serves the model form
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String NewUser(Model model){
        return "newuser";
    }

    // This would be used from a form
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNewUser(Model model, @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors() || userRepo.findByEmail(user.getEmail()) != null){
            return "placeholder";
        }

        // TODO: Use this to login after saving
        User u = userRepo.save(user);

        // TODO: Maybe send to the student creation page?
        return "placeholder";
    }

    // Used for post request using JSON
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<User> CreateNewUser (@RequestBody User user){
        if(userRepo.findByEmail(user.getEmail()) != null){
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        }

        User new_user = userRepo.save(user);

        return new ResponseEntity<User>(new_user, HttpStatus.OK);
    }
}
