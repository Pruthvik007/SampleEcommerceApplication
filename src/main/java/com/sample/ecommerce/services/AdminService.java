package com.sample.ecommerce.services;

import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;

public interface AdminService {
    User createEmployee(EmployeeRegisterDto employeeRegisterDto) throws UserException;
}
