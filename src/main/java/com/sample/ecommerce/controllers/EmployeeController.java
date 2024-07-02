package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.CategoryCreateDto;
import com.sample.ecommerce.dtos.CategoryUpdateDto;
import com.sample.ecommerce.dtos.ProductCreateDto;
import com.sample.ecommerce.dtos.ProductUpdateDto;
import com.sample.ecommerce.entities.Category;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.exceptions.CategoryException;
import com.sample.ecommerce.exceptions.ProductException;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("create-product")
    public Response<Product> createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) throws CategoryException, ProductException {
        return Response.<Product>builder().data(employeeService.createProduct(productCreateDto)).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("update-product")
    public Response<Product> updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto) throws CategoryException, ProductException {
        return Response.<Product>builder().data(employeeService.updateProduct(productUpdateDto)).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("create-category")
    public Response<Category> createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) throws CategoryException {
        return Response.<Category>builder().data(employeeService.createCategory(categoryCreateDto)).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("update-category")
    public Response<Category> updateCategory(@RequestBody @Valid CategoryUpdateDto categoryUpdateDto) throws CategoryException {
        return Response.<Category>builder().data(employeeService.updateCategory(categoryUpdateDto)).status(Response.Status.SUCCESS).build();
    }
}
