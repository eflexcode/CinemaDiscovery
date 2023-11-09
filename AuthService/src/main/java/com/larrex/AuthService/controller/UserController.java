package com.larrex.AuthService.controller;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserModel userModel, HttpServletRequest httpServletRequest){

        return userService.createUser(userModel,httpServletRequest);

    }

}
