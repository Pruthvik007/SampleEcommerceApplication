package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AddressDto {
    private String title;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
}
