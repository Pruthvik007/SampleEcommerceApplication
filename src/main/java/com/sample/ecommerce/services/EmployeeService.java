package com.sample.ecommerce.services;

import com.sample.ecommerce.dtos.CategoryCreateDto;
import com.sample.ecommerce.dtos.CategoryUpdateDto;
import com.sample.ecommerce.dtos.ProductCreateDto;
import com.sample.ecommerce.dtos.ProductUpdateDto;
import com.sample.ecommerce.entities.Category;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.exceptions.CategoryException;
import com.sample.ecommerce.exceptions.ProductException;

public interface EmployeeService {

    Product createProduct(ProductCreateDto productCreateDto) throws CategoryException, ProductException;

    Product updateProduct(ProductUpdateDto productUpdateDto) throws ProductException, CategoryException;

    Category createCategory(CategoryCreateDto categoryCreateDto) throws CategoryException;

    Category updateCategory(CategoryUpdateDto categoryUpdateDto) throws CategoryException;
}
