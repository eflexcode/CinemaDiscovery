package com.larrex.AccountServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Cinema {

    @Id
    private String organisationId;
    private String organisationName;
    private String fullAddress;
    private String email;
    @JsonIgnore
    private String password;
    private String imageUrl;
    private String organisationDescription;
    private Address address;
    private String openAt;
    private String closeAt;

    private Date createdAt;
    private Date updateAt;

}
