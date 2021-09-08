package com.infosys.oauth2login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Before {
    @RequestMapping("/before")
    public Object before(){
        return "Welcome to before login";
    }
}
