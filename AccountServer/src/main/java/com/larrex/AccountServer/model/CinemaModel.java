package com.larrex.AccountServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class CinemaModel {

    private String organisationName;
    private String fullAddress;
    private String email;
    private String imageUrl;
    private String organisationDescription;

    private String openAt;
    private String closeAt;

    private Date createdAt;
    private Date updateAt;

}
