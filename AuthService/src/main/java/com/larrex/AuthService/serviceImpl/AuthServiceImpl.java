package com.larrex.AuthService.serviceImpl;

import com.larrex.AuthService.client.AccountClient;
import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.entity.VerificationToken;
import com.larrex.AuthService.event.VerificationEvent;
import com.larrex.AuthService.model.JwtToken;
import com.larrex.AuthService.model.LoginModel;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.repository.VerificationRepository;
import com.larrex.AuthService.sevice.AuthService;
import com.larrex.AuthService.util.JwtUtil;
import com.larrex.AuthService.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountClient accountClient;
    private final ApplicationEventPublisher eventPublisher;
    private final VerificationRepository verificationRepository;
    private final JwtUtil jwtUtil;


    @Override
    public User registerAccount(UserModel userModel) {

        ResponseEntity<User> userRequestEntity = accountClient.createUser(userModel);

        if (userRequestEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Something went wrong");
        }

        if (userRequestEntity.getStatusCode() == HttpStatus.CREATED) {
            eventPublisher.publishEvent(new VerificationEvent(this, userModel.getEmail()));
        }

        return accountClient.createUser(userModel).getBody();
    }

    @Override
    public void sendVerificationEmail(String email) {

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setOwnerId(email);
        verificationToken.setExpirationDate(new Date(System.currentTimeMillis() + Util.exp_time));
        verificationToken.setToken(token);

        verificationRepository.save(verificationToken);

        // supposed to send email here but i would not do that so i print
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + token);

    }

    @Override
    public String verifyToken(String token) {

        VerificationToken verificationToken = verificationRepository.findByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with token"));

        if (verificationToken.getExpirationDate().before(new Date())) {
            //  expired
            return "Token expired";
        } else {

            ResponseEntity<User> user = accountClient.verifyUser(verificationToken.getOwnerId());
            if (user.getStatusCode() == HttpStatus.OK) {
                verificationRepository.delete(verificationToken);
                return ("User verified to login");
            } else {
                throw new ResponseStatusException(HttpStatus.GONE, "Something went wrong");
            }
        }
    }

    @Override
    public String verifyTokenExpired(String oldVerificationToken) {

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = verificationRepository.findByToken(oldVerificationToken).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with token"));
        verificationToken.setExpirationDate(new Date(System.currentTimeMillis() + Util.exp_time));
        verificationToken.setToken(token);
        verificationRepository.save(verificationToken);
        // supposed to send email here but i would not do that so i print
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + token);

        return "new token send to "+verificationToken.getOwnerId();
    }

    @Override
    public JwtToken login(UserDetails userDetails) {
        return new JwtToken(jwtUtil.generateJwt(userDetails));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                ResponseEntity<User> userResponseEntity = accountClient.getUserEmail(username);

                if (userResponseEntity.getStatusCode() == HttpStatus.OK){
                    return userResponseEntity.getBody();
                }else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found");

            }
        };
    }

    @Override
    public User getUser() {
        return getLoggedInUser();
    }

    @Override
    public User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("ddddddddddddddddddddddddd"+email);
        return accountClient.getUserEmail(email).getBody();
    }
}
