package com.eventwebapp.controllers;

import com.eventwebapp.entities.event.Event;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Xavier on 11/4/2015.
 */

// maybe this should be mapped to /university/{uniid}/rso ? since an rso is affiliated with a university
@Controller
@RequestMapping("/rsos")
public class RsoRESTController {

    @Autowired
    private
    RSORepo rsoRepo;

    @Autowired
    private
    RSOTypeRepo rsoTypeRepo;

    @Autowired
    private
    UniversityRepo universityRepo;

    @Autowired
    private
    UserRepo userRepo;

    @Autowired
    private
    RsoMemberRepo rsoMemberRepo;

    @Autowired
    private
    EventRepo eventRepo;

    // GET the list of all rsos on a page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listRSOs(Model model){
        // TODO: attach the list of rsos to the model
        model.addAttribute("list", rsoRepo.findAll());
        model.addAttribute("listName", "All RSOs");
        // TODO: send to rso list page
        return "newlayout/rsos";
    }

    // GET the rso creation page
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newRSO(Model model, RsoForm rsoForm){
        // TODO: attach the rsotypes and maybe the university it's affiliated with
        model.addAttribute("types", rsoTypeRepo.findAll());
        model.addAttribute("universities", universityRepo.findAll());

        return "newlayout/createRso";
    }

    // POST to the new rso page
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewRSO(Model model, @Valid RsoForm rsoForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("types", rsoTypeRepo.findAll());
            model.addAttribute("universities", universityRepo.findAll());

            System.out.println(bindingResult.getAllErrors().toString());

            // TODO: send the user back to the rso creation page
            return "newlayout/createRso";
        }

        // TODO: 11/13/15 check to make sure none of the emails are repeated

        RSO rso = rsoForm.createRSO();
        if(rso == null){
            System.out.println("rso is null");
            // either add error to binding result or as a message to be displayed?
            model.addAttribute("errorMessage", "Something went wrong, we could not create the RSO, you may try again");

            // Send user back to /new
            return "newlayout/createRso";
        }

        rsoRepo.save(rso);


        // TODO: redirect to the new rso page.
        return String.format("redirect:/rso/%d", rso.getId_rso());
    }

    // GET a single rso page
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String rsoPage(Model model, @PathVariable("id") Long id){
        // attach the rso to the model
        if(!rsoRepo.exists(id)){
            // TODO: Send the user to a DNE page
            return "test/placeholder";
        }

        model.addAttribute("rso", rsoRepo.findOne(id));
        // TODO: send user to rso page
        return "test/placeholder";
    }

    // GET my rsos
    @RequestMapping(value = "/myrsos", method = RequestMethod.GET)
    public String myRSOs(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }

        Long userId = userRepo.findByUsername(principal.getName()).getId_user();
        // Add list name Attribute. Ie. "Your RSOs" or "All RSOs"
        List<Long> rsoIds = getUserRSOs(userId);
        List<RSO> rsoList = rsoRepo.findAll(rsoIds);

        model.addAttribute("listName", "RSOs You Are In");
        model.addAttribute("list", rsoList);

        return "newlayout/rsos"; // Should be sent back to rso page
    }

    @RequestMapping(value = "/myrsos/events", method = RequestMethod.GET)
    public String myRSOsEvents(Model model, Principal principal){
        List<Event> eventList = new ArrayList<>();
        if(principal != null){
            Long userId = userRepo.findByUsername(principal.getName()).getId_user();
            List<Long> rsoIds = getUserRSOs(userId);
            List<RSO> rsoList = rsoRepo.findAll(rsoIds);
            rsoList.parallelStream()
                    .map(RSO::getId_rso)
                    .forEach(id -> eventList.addAll(eventRepo.findByHost_Rso(id)));
        }

        /*  See if this works? longer, but I need to know how reduce works
        eventList = rsoList.parallelStream()
                .map(RSO::getId_rso)
                .map(id -> eventRepo.findByHost_Rso(id))
                .reduce(new ArrayList<Event>(0), (a, b) -> {
                    a.addAll(b);
                    return b;
                });
        */

        model.addAttribute("list", eventList);
        model.addAttribute("listName", "Events Hosted By Your RSOs");

        return "newlayout/events";
    }



    private boolean doesUserExist(@Email String email){
        return userRepo.findByEmail(email) != null;
    }

    private boolean addRSOMembersByEmail(List<String> members, Long rsoId){
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

    private List<Long> getUserRSOs(Long userid){
        return rsoMemberRepo.findByMember(userid).stream()
                .map(RsoMember::getAffiliated_rso)
                .collect(Collectors.toList());
    }

    private boolean addMemberById(Long userId, Long rsoId){
        RsoMember m = rsoMemberRepo.findByMemberAndRso(userId, rsoId);

        // also check if user exists here as well
        if(userRepo.exists(userId) && (m != null && m.getAffiliated_rso() == rsoId)){
            return false;
        }

        rsoMemberRepo.save(new RsoMember(rsoId, userId));
        return true;
    }

    // TODO: Add member by email
}
