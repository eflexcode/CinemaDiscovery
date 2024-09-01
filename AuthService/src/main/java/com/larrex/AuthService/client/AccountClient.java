package com.larrex.AuthService.client;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ACCOUNTSEVER", url = "http://localhost:8095/account/")
public interface AccountClient {

    @PostMapping("user/create_account")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<User> createUser(@RequestBody UserModel userModel);

    @GetMapping("user/with_email/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<User> getUserEmail(@PathVariable(name = "email") String email);

    @PostMapping("user/verify/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<User> verifyUser(@PathVariable(name = "email") String email);
}
