package com.eventec.eventec.controllers;

import com.eventec.eventec.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> subscribe(@RequestParam Long userid, @RequestParam Long eventId) {
        try {
            subscriptionService.subscribe(userid, eventId);
            return ResponseEntity.ok("Inscrição realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<?> unsubscribe(@PathVariable Long subscriptionId) {
        try {
            subscriptionService.unsubscribe(subscriptionId);
            return ResponseEntity.ok("Desinscrição realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
