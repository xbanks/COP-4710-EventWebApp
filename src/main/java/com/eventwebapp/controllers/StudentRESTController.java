package com.eventwebapp.controllers;

import com.eventwebapp.entities.users.Student;
import com.eventwebapp.repositories.StudentRepo;
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
@RequestMapping("/user/student")
public class StudentRESTController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentRepo studentRepo;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newStudent(Model model){
        return "placeholder";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNewStudent(Model model, @Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "placeholder";
        }

        // If there is no user in the database with the given id_student value
        // The a user must be created before that user can become a student
        if(!userRepo.exists(student.getId_student())){

            // TODO: Send the user back to the login/signup page
            return "placeholder";
        }

        studentRepo.save(student);

        // TODO: Maybe send to a dashboard view?
        return "placeholder";
    }


}
