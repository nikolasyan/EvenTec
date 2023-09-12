package com.eventec.eventec.controllers;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.services.UserItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserItemService userItemService;

    @GetMapping("/signIn")
    public String showCreatedForm(UserItem userItem) { return "myEvent";}

    @PostMapping("/signInUser")
    public String signInUserItem(@Valid UserItem userItem, Model model, RedirectAttributes redirectAttributes){
        Optional<UserItem> item = this.userItemService.getByEmailAndPassword(userItem.getEmail(), userItem.getPassword());

        if(item.isPresent()){
            return "redirect:/myAccount";
        }
        else {
            redirectAttributes.addFlashAttribute("erro", "Campos inválidos. Tente novamente.");
            return "redirect:/loginAndRegister";

        }
    }
}

