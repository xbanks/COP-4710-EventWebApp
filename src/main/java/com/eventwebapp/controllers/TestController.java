package com.eventwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 11/5/15.
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test(Model model){
        List<Long> list = new ArrayList<Long>(5);
        list.add(1L);
        list.add(25L);
        model.addAttribute("object", list);
        return "test";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String testPost(List<Long> list){
        System.out.println(list);
        return "test";
    }
}
