package com.eventec.eventec.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import com.eventec.eventec.config.LoginResponse;
import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.services.UserItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserItemService userItemService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        Optional<UserItem> user = userItemService.getByEmailAndPassword(email, password);
        if (user.isPresent()) {
            // Login successful
            return ResponseEntity.ok().body(new LoginResponse("Login Successful"));

        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");

        }
    }
}