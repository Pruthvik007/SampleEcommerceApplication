package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.CategoryCreateDto;
import com.sample.ecommerce.dtos.CategoryUpdateDto;
import com.sample.ecommerce.dtos.ProductCreateDto;
import com.sample.ecommerce.dtos.ProductUpdateDto;
import com.sample.ecommerce.entities.Category;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.exceptions.CategoryException;
import com.sample.ecommerce.exceptions.ProductException;
import com.sample.ecommerce.helpers.AppConstants;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("create-product")
    public Response<Product> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        log.info("Creating product {}", productCreateDto);
        try {
            return Response.<Product>builder().data(employeeService.createProduct(productCreateDto)).status(Response.Status.SUCCESS).build();
        } catch (ProductException | CategoryException e) {
            return Response.<Product>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Creating Product", e);
            return Response.<Product>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @PostMapping("update-product")
    public Response<Product> updateProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        try {
            return Response.<Product>builder().data(employeeService.updateProduct(productUpdateDto)).status(Response.Status.SUCCESS).build();
        } catch (ProductException | CategoryException e) {
            return Response.<Product>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Updating Product", e);
            return Response.<Product>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @PostMapping("create-category")
    public Response<Category> createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        log.info("Create Category: {}", categoryCreateDto);
        try {
            return Response.<Category>builder().data(employeeService.createCategory(categoryCreateDto)).status(Response.Status.SUCCESS).build();
        } catch (CategoryException e) {
            return Response.<Category>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Creating Category", e);
            return Response.<Category>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @PostMapping("update-category")
    public Response<Category> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        try {
            return Response.<Category>builder().data(employeeService.updateCategory(categoryUpdateDto)).status(Response.Status.SUCCESS).build();
        } catch (CategoryException e) {
            return Response.<Category>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Updating Category", e);
            return Response.<Category>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Employee From Protected Route";
    }
}
