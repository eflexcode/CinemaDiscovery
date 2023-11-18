package com.larrex.AccountServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class UserModel {

    private String displayName;
    private String email;
    private String password;
    private String imageUrl;
    private String bio;
    private Boolean online;
    private String createdAt;
    private String updateAt;

}
