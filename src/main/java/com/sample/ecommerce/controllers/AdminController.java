package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.AppConstants;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("create-employee")
    public Response<User> createEmployee(@RequestBody EmployeeRegisterDto employeeRegisterDto) {
        try {
            return Response.<User>builder().data(adminService.createEmployee(employeeRegisterDto)).status(Response.Status.SUCCESS).build();
        } catch (UserException e) {
            return Response.<User>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Creating Employee", e);
            return Response.<User>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Logged in user: {}", loggedInUser);
        return "Hello Admin From Protected Route";
    }
}