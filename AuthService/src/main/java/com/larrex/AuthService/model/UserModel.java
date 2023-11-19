package com.larrex.AuthService.model;

import lombok.Data;

@Data
public class UserModel {

    private String displayName;
    private final String email;
    private String password;
    private String imageUrl;
    private String bio;
    private Boolean online;
    private String createdAt;
    private String updateAt;

}
