package com.eventec.eventec.controllers;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.services.UserItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RegisterFormController {

    @Autowired
    private UserItemService userItemService;

    @GetMapping("/register")
    public String showCreatedForm(UserItem userItem) {
        return "login";
    }

    @PostMapping("/registerUser")
    public String createUserItem(@Valid UserItem userItem, BindingResult result, Model model) {
        UserItem item = new UserItem();
        item.setUserid(userItem.getUserid());
        item.setUserName(userItem.getUserName());
        item.setEmail(userItem.getEmail());
        item.setPassword(userItem.getPassword());

        userItemService.save((userItem));
        return "redirect:http://localhost:3000/";
    }
    @GetMapping("/myAccount")
    public ResponseEntity<Object> consultaUsuario() {
        return ResponseEntity.status(HttpStatus.OK).body(userItemService.consultaUsuario());
    }
}
/*
    @GetMapping("/myAccount")
    public ResponseEntity<Object> consultaUsuario(@RequestParam String email, @RequestParam String password) {
        Optional<UserItem> user = userItemService.getByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
*/