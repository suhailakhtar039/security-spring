package com.sec1.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {
    @GetMapping("/cards")
    public String getCards(){
        return "Your Card details: ";
    }
}
