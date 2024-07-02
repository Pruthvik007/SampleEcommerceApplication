package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("create-employee")
    public Response<User> createEmployee(@RequestBody @Valid EmployeeRegisterDto employeeRegisterDto) throws UserException {
        return Response.<User>builder().data(adminService.createEmployee(employeeRegisterDto)).status(Response.Status.SUCCESS).build();
    }
}