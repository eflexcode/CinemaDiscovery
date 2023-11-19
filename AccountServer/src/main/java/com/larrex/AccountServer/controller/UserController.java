package com.larrex.AccountServer.controller;

import com.larrex.AccountServer.entity.Address;
import com.larrex.AccountServer.model.UserModel;
import com.larrex.AccountServer.entity.User;
import com.larrex.AccountServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("create_account")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @PutMapping("location/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateLocation(@PathVariable("user_id") String userId, @RequestBody Address address) {
        return userService.updateLocation(userId, address);
    }

    @PutMapping("{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("user_id") String userId, @RequestBody UserModel userModel) {
        return userService.updateUser(userModel, userId);
    }

    @GetMapping("{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable(name = "user_id") String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("with_email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserWithEmail(@PathVariable(name = "email") String email) {
        return userService.getUserByEmail(email);
    }
    @GetMapping("get")
    public String  getString() {
        return "user_id";
    }

    @DeleteMapping("{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("user_id") String userId){
        userService.deleteUser(userId);
    }

}
