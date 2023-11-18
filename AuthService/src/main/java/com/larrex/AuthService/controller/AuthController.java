package com.larrex.AuthService.controller;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.sevice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerAccount(@RequestBody UserModel userModel){
        return authService.registerAccount(userModel);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public String login(){
        return "login test";
    }

    @PostMapping("verify_email_token")
    @ResponseStatus(HttpStatus.OK)
    public String verifyEmail(){
        return "verify test";
    }

    @PostMapping("verify_email_toke_expired")
    @ResponseStatus(HttpStatus.OK)
    public String createVerifyEmail(){
        return "verify test";
    }

}
