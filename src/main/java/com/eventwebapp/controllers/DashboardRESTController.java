package com.eventwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xavier on 11/5/15.
 */

@Controller
@RequestMapping("")
public class DashboardRESTController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String dashboard(Model model){
        return "redirect:/events";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String dashboardPOST(Model model){
        return "redirect:/events";
    }
}
