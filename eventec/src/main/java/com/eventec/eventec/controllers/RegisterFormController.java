package com.eventec.eventec.controllers;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.services.UserItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterFormController {

    @Autowired
    private UserItemService userItemService;

    @GetMapping("/register")
    public String showCreatedForm(UserItem userItem) { return "login";}

    @PostMapping("/registerUser")
    public  String createUserItem(@Valid UserItem userItem, BindingResult result, Model model){
        UserItem item = new UserItem();
        item.setUserid(userItem.getUserid());
        item.setUserName(userItem.getUserName());
        item.setEmail(userItem.getEmail());
        item.setPassword(userItem.getPassword());

        userItemService.save((userItem));
        return "redirect:/";
    }
}
