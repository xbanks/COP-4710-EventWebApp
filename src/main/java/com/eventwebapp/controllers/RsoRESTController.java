package com.eventwebapp.controllers;

import com.eventwebapp.entities.rso.RSO;
import com.eventwebapp.repositories.RSORepo;
import com.eventwebapp.repositories.RSOTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Xavier on 11/4/2015.
 */

// maybe this should be mapped to /university/{uniid}/rso ? since an rso is affiliated with a university
@Controller
@RequestMapping("/rso")
public class RsoRESTController {

    @Autowired
    RSORepo rsoRepo;

    @Autowired
    RSOTypeRepo rsoTypeRepo;

    // GET the rso creation page
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRSO(Model model){
        // TODO: attach the rsotypes and maybe the university it's affiliated with

        return "placeholder";
    }

    // GET a single rso page
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String rsoPage(Model model, @PathVariable("id") Long id){
        // attach the rso to the model
        if(!rsoRepo.exists(id)){
            // TODO: Send the user to a DNE page
            return "placeholder";
        }

        model.addAttribute("rso", rsoRepo.findOne(id));
        // TODO: send user to rso page
        return "placeholder";
    }

    // POST to the new rso page
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNewRSO(Model model, @Valid RSO rso, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // TODO: send the user back to the rso creation page
            return "placeholder";
        }

        rsoRepo.save(rso);

        // TODO: redirect to the new rso page.
        return String.format("redirect:/placeholder/", 0);
    }

    // GET the list of all rsos on a page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listRSOs(Model model){
        // TODO: attach the list of rsos to the model

        // TODO: send to rso list page
        return "placeholder";
    }
}
