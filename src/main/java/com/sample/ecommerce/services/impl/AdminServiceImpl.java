package com.sample.ecommerce.services.impl;

import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.BeanValidator;
import com.sample.ecommerce.repositories.UserRepository;
import com.sample.ecommerce.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createEmployee(EmployeeRegisterDto employeeRegisterDto) throws UserException {
        String errorMessage = BeanValidator.isValidEmployeeDetails(employeeRegisterDto);
        if (errorMessage != null) {
            throw new UserException(errorMessage);
        }
        return userRepository.save(new User(employeeRegisterDto.getName(), employeeRegisterDto.getEmail(), passwordEncoder.encode(employeeRegisterDto.getPassword()), employeeRegisterDto.getUserRole()));
    }
}
