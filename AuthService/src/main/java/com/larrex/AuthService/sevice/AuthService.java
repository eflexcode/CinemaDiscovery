package com.larrex.AuthService.sevice;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.JwtToken;
import com.larrex.AuthService.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {

    User registerAccount(UserModel userModel);

    void sendVerificationEmail(String email);

    String verifyToken(String verificationToken);

    String verifyTokenExpired(String oldVerificationToken);

    JwtToken login(UserDetails userDetails);

    UserDetailsService userDetailsService();

    User getUser();

    User getLoggedInUser();
}
