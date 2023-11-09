package com.larrex.AuthService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class CinemaModel {

    private String organisationName;
    private String address;
    private String email;
    private String password;
    private String imageUrl;
    private String organisationDescription;

    private Date openAt;
    private Date closeAt;

    private Date createdAt;
    private Date updateAt;

}
