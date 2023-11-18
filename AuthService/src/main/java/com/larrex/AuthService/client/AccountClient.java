package com.larrex.AuthService.client;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = "ACCOUNTSEVER", url = "http://localhost:8095/account/")
public interface AccountClient {

    @PostMapping("user/create_account")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody UserModel userModel);



}
