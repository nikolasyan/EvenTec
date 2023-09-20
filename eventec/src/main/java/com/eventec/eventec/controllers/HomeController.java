package com.eventec.eventec.controllers;

import com.eventec.eventec.services.EventItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private EventItemService eventItemService;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "form";
    }

    @GetMapping("/loginAndRegister")
    public String login() {
        return "login";
    }

    @GetMapping("/my-events")
    public String myEvents() {
        return "myEvent";
    }

    @GetMapping("/event")
    public String allEvents() {
        return "event";
    }

    @GetMapping("/allEvents")
    public ModelAndView addev() {
        ModelAndView modelAndView = new ModelAndView("event");
        modelAndView.addObject("eventItems", eventItemService.getAll());
        return modelAndView;
    }


}
