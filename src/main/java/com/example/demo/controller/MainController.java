package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to the home page! This is accessible to everyone.";
    }

    @GetMapping("/user")
    @ResponseBody
    public String userHome(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        jwt.getClaims().forEach((key, value) -> {
        });
        return "Hello, User " + username + "! You've accessed a protected endpoint for users with role 'USER'.\n" +
                "Your JWT subject: " + jwt.getSubject();
    }

    @GetMapping("/admin")
    @ResponseBody
    public String adminHome(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        jwt.getClaims().forEach((key, value) -> {
        });
        return "Hello, Admin " + username + "! You've accessed a protected endpoint for users with role 'ADMIN'.\n" +
                "Your JWT subject: " + jwt.getSubject();
    }
}