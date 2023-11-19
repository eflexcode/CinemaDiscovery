package com.larrex.AuthService.event.listener;

import com.larrex.AuthService.event.VerificationEvent;
import com.larrex.AuthService.sevice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationEvenListener implements ApplicationListener<VerificationEvent> {

    private final AuthService authService;

    @Override
    public void onApplicationEvent(VerificationEvent event) {
        authService.sendVerificationEmail(event.getEmail());
    }
}
