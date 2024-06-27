package com.sample.ecommerce.services;

import com.sample.ecommerce.dtos.UserRegisterDto;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;
import org.springframework.data.domain.Page;

public interface EcommerceService {
    Page<Product> searchProducts(Long categoryId, String searchTerm, int page);

    User registerCustomer(UserRegisterDto userRegisterDto) throws UserException;
}
