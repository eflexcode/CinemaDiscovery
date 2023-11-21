package com.larrex.AuthService.sevice;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.JwtToken;
import com.larrex.AuthService.model.UserModel;

public interface AuthService {

    User registerAccount(UserModel userModel);

    void sendVerificationEmail(String email);

    String verifyToken(String verificationToken);
    String verifyTokenExpired(String oldVerificationToken);

}
