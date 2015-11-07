package com.eventwebapp.controllers;

import com.eventwebapp.entities.rso.RSO;
import com.eventwebapp.entities.rso.RsoMember;
import com.eventwebapp.forms.RsoForm;
import com.eventwebapp.repositories.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

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

    @Autowired
    UniversityRepo universityRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RsoMemberRepo rsoMemberRepo;

    // GET the rso creation page
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRSO(Model model, RsoForm rsoForm){
        // TODO: attach the rsotypes and maybe the university it's affiliated with
        model.addAttribute("types", rsoTypeRepo.findAll());
        model.addAttribute("universities", universityRepo.findAll());

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
    public String createNewRSO(Model model, @Valid RsoForm rsoForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // TODO: send the user back to the rso creation page
            return "placeholder";
        }

        RSO rso = rsoForm.createRSO();
        if(rso == null){
            // either add error to binding result or as a message to be displayed?
            model.addAttribute("errorMessage", "Something went wrong, we could not create the RSO, you may try again");

            // Send user back to /new
            return "placeholder";
        }

        rsoRepo.save(rso);


        // TODO: redirect to the new rso page.
        return String.format("redirect:/placeholder/", rso.getId_rso());
    }

    // GET the list of all rsos on a page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listRSOs(Model model){
        // TODO: attach the list of rsos to the model

        // TODO: send to rso list page
        return "placeholder";
    }

    private boolean doesUserExist(@Email String email){
        return userRepo.findByEmail(email) != null;

    }

    private boolean addRSOMembersByEmail(List<String> members, Long rsoId){
//        for (String s : members) {
//            Long userId = userRepo.findByEmail(s).getId_user();
//            if(rsoMemberRepo.findByMemberAndRso(userId, rsoId) == null){
//                rsoMemberRepo.save(new RsoMember(rsoId, userId));
//            }
//        }

        members.stream()
                .map(m -> userRepo.findByEmail(m).getId_user())
                .filter(id -> rsoMemberRepo.findByMemberAndRso(id, rsoId) == null)
                .forEach(id -> rsoMemberRepo.save(new RsoMember(rsoId, id)));

        return true;
    }

    private boolean addRSOMembersById(List<Long> members, Long rsoId){
        members.stream()
                .filter(id -> userRepo.exists(id))
                .filter(id -> rsoMemberRepo.findByMemberAndRso(id, rsoId) == null)
                .forEach(id -> rsoMemberRepo.save(new RsoMember(rsoId, id)));

        return true;
    }

    private boolean addMemberById(Long userId, Long rsoId){
        RsoMember m = rsoMemberRepo.findByMember(userId);

        // also check if user exists here as well
        if(userRepo.exists(userId) && (m != null && m.getAffiliated_rso() == rsoId)){
            return false;
        }

        rsoMemberRepo.save(new RsoMember(rsoId, userId));
        return true;
    }

    // TODO: Add member by email
}
