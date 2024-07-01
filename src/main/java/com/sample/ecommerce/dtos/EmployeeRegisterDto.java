package com.sample.ecommerce.dtos;

import com.sample.ecommerce.entities.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class EmployeeRegisterDto extends UserRegisterDto {
    @NotNull(message = "User Role is Required")
    private UserRole userRole;

    public EmployeeRegisterDto(String name, String email, String password, UserRole userRole) {
        super(name, email, password);
        this.userRole = userRole;
    }
}
