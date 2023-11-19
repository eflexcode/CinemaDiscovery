package com.larrex.AuthService.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class VerificationToken {

    private String id;
    private String ownerId;
    private String token;
    private Date expirationDate;

}
