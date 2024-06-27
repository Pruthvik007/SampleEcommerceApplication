package com.sample.ecommerce.dtos;

import com.sample.ecommerce.entities.UserRole;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmployeeRegisterDto extends UserRegisterDto {
    private UserRole userRole;
}
