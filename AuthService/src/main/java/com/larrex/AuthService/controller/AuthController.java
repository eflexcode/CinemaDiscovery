package com.larrex.AuthService.controller;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.JwtToken;
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

    @PostMapping("verify")
    @ResponseStatus(HttpStatus.OK)
    public String verifyEmail(@RequestParam(name = "token") String token){
        return authService.verifyToken(token);
    }

    @PostMapping("expired_token")
    @ResponseStatus(HttpStatus.OK)
    public String createVerifyEmail(@RequestParam(name = "token")String token){
        return authService.verifyTokenExpired(token);
    }

}
