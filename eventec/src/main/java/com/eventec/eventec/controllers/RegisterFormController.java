package com.eventec.eventec.controllers;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.services.UserItemService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class RegisterFormController {

    @Autowired
    private UserItemService userItemService;

    @GetMapping("/register")
    public String showCreatedForm(UserItem userItem) {
        return "login";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/myAccount")
    public ResponseEntity<Object> consultaUsuario() {
        return ResponseEntity.status(HttpStatus.OK).body(userItemService.consultaUsuario());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public @ResponseBody ResponseEntity<UserItem> createUser(@RequestBody UserItem userItem) {
        UserItem createdUser = userItemService.createUserItem(userItem);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}