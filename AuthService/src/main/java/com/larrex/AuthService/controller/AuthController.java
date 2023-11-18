package com.larrex.AuthService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class AuthController {

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerAccount(){
        return "creation test";
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(){
        return "login test";
    }

    @PostMapping("verify_email_token")
    @ResponseStatus(HttpStatus.CREATED)
    public String verifyEmail(){
        return "verify test";
    }

    @PostMapping("verify_email_toke_expired")
    @ResponseStatus(HttpStatus.CREATED)
    public String createVerifyEmail(){
        return "verify test";
    }

}
