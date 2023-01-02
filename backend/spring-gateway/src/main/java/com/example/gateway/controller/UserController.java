package com.example.gateway.controller;

import com.example.gateway.domain.User;
import com.example.gateway.dto.UserDto;
import com.example.gateway.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public Mono<ResponseEntity> signUpUser(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        String userEmail = principal.getAttributes().get("email").toString();
        String userName = principal.getAttributes().get("name").toString();

        return userService.findUserByEmail(userEmail)
                .switchIfEmpty(userService.saveUser(User.builder().email(userEmail).userName(userName).build()))
                .map(user -> ResponseEntity.ok("user is registered successfully"));
    }

    @GetMapping("/user/detail")
    public Mono<ResponseEntity> getUserInfo(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        String userEmail = principal.getAttributes().get("email").toString();

        return userService.findUserByEmail(userEmail)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is no user")))
                .map(user -> ResponseEntity.ok(UserDto.FROM(user)));
    }
}
