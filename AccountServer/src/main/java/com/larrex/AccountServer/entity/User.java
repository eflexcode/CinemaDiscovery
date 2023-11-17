package com.larrex.AccountServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class User {

    @Id
    private String id;
    private String displayName;
    private String email;
    @JsonIgnore
    private String password;
    private String imageUrl;
    private String bio;
    private Boolean online;
    private Address address;

    private Date createdAt;
    private Date updateAt;

}
