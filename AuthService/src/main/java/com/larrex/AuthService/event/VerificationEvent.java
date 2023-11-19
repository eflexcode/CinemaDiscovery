package com.larrex.AuthService.event;

import org.springframework.context.ApplicationEvent;

public class VerificationEvent extends ApplicationEvent {

    private String email;

    public VerificationEvent(Object source,String email){
        super(source);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

}
