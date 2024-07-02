package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegisterDto extends UserLoginDto {

    @NotBlank(message = "User Name is Required")
    @Size(min = 5, max = 20, message = "User Name must be between 5 and 20 characters")
    private String name;

    public UserRegisterDto(String name, String email, String password) {
        super(email, password);
        this.name = name;
    }
}
