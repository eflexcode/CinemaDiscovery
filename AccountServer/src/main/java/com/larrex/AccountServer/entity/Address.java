package com.larrex.AccountServer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String country;
    private String city;
    private Double lon;
    private Double lat;

}
