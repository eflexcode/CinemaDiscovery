package com.larrex.AuthService.serviceImpl;

import com.larrex.AuthService.client.AccountClient;
import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.sevice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountClient accountClient;

    @Override
    public User registerAccount(UserModel userModel) {
        return accountClient.createUser(userModel);
    }
}
