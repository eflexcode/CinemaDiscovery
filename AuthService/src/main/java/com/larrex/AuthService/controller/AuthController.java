package com.larrex.AuthService.controller;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.JwtToken;
import com.larrex.AuthService.model.LoginModel;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.sevice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerAccount(@RequestBody UserModel userModel) {
        return authService.registerAccount(userModel);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public JwtToken login(@RequestBody LoginModel loginModel) {
        System.out.println("ddddddddddddddddddddddddddddddddddddd login " + loginModel);

        Authentication authentication =
                authenticationManager.
                        authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        if (authentication.isAuthenticated()) {
            final UserDetails userDetails = authService.userDetailsService().loadUserByUsername(loginModel.getEmail());
            return authService.login(userDetails);
//        } else {
//            throw new UsernameNotFoundException("invalid  user");
//        }
//        return new JwtToken("ssssssssssssssssssssssssssssssssssssssssssssssss");
    }

    @PostMapping("verify")
    @ResponseStatus(HttpStatus.OK)
    public String verifyEmail(@RequestParam(name = "token") String token) {
        return authService.verifyToken(token);
    }

    @PostMapping("expired_token")
    @ResponseStatus(HttpStatus.OK)
    public String createVerifyEmail(@RequestParam(name = "token") String token) {
        return authService.verifyTokenExpired(token);
    }

    @GetMapping("get_user")
    @ResponseStatus(HttpStatus.OK)
    public User getUser() {
        return authService.getUser();
    }

}
