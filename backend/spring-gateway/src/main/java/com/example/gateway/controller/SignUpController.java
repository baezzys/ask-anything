package com.example.gateway.controller;

import com.example.gateway.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        userService.saveUser(principal.getAttributes().get("email").toString()
                , principal.getAttributes().get("name").toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
