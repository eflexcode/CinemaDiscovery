package com.larrex.AuthService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Cinema {

    @Id
    private String id;
    private String organisationName;
    private String address;
    private String email;
    @JsonIgnore
    private String password;
    private String imageUrl;
    private String organisationDescription;

    private Date openAt;
    private Date closeAt;

    private Date createdAt;
    private Date updateAt;

}
