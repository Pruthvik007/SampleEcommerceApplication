package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserLoginDto {
    private String email;
    private String password;
}
