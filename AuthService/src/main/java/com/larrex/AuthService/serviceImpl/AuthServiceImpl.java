package com.larrex.AuthService.serviceImpl;

import com.larrex.AuthService.client.AccountClient;
import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.entity.VerificationToken;
import com.larrex.AuthService.event.VerificationEvent;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.sevice.AuthService;
import com.larrex.AuthService.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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

    @Override
    public User registerAccount(UserModel userModel) {

        ResponseEntity<User> userRequestEntity = accountClient.createUser(userModel);

        if (userRequestEntity.getStatusCode() != HttpStatus.CREATED){
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (userRequestEntity.getStatusCode() == HttpStatus.CREATED){
            eventPublisher.publishEvent(new VerificationEvent(this, userModel.getEmail()));
        }

        return accountClient.createUser(userModel).getBody();
    }

    @Override
    public void sendVerificationEmail(String email) {

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setOwnerId(email);
        verificationToken.setExpirationDate(new Date(Util.exp_time));
        verificationToken.setToken(UUID.randomUUID().toString());

    }
}
