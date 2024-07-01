package com.sample.ecommerce.services.impl;

import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.entities.UserStatus;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.repositories.UserRepository;
import com.sample.ecommerce.services.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User createEmployee(EmployeeRegisterDto employeeRegisterDto) throws UserException {
        if (userRepository.findByEmail(employeeRegisterDto.getEmail()).isPresent()) {
            throw new UserException("Employee Already Exists With Given Email");
        }
        User employee = new User(employeeRegisterDto.getName(), employeeRegisterDto.getEmail(), passwordEncoder.encode(employeeRegisterDto.getPassword()), employeeRegisterDto.getUserRole());
        employee.setStatus(UserStatus.CREATED);
        return userRepository.save(employee);
    }
}
