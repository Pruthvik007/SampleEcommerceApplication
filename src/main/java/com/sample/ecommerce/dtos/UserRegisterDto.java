package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserRegisterDto extends UserLoginDto {
    private String name;
}
