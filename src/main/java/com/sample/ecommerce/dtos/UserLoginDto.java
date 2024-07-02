package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserLoginDto {
    @NotBlank(message = "User Email is Required")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;
}
