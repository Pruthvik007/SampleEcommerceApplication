package com.sample.ecommerce.services.impl;

import com.sample.ecommerce.dtos.UserRegisterDto;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.entities.UserRole;
import com.sample.ecommerce.entities.UserStatus;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.AppConstants;
import com.sample.ecommerce.helpers.BeanValidator;
import com.sample.ecommerce.repositories.ProductRepository;
import com.sample.ecommerce.repositories.UserRepository;
import com.sample.ecommerce.services.EcommerceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EcommerceServiceImpl implements EcommerceService {
    private static final Logger log = LoggerFactory.getLogger(EcommerceServiceImpl.class);
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<Product> searchProducts(Long categoryId, String searchTerm, int page) {
        return productRepository.findProductsByCategoryIdAndSearchTerm(categoryId, searchTerm, PageRequest.of(page, AppConstants.PAGE_SIZE));
    }

    @Override
    public User registerCustomer(UserRegisterDto userRegisterDto) throws UserException {
        String errorMessage = BeanValidator.isValidUserDetails(userRegisterDto);
        if (errorMessage != null) {
            throw new UserException(errorMessage);
        }
        User user = new User(userRegisterDto.getName(), userRegisterDto.getEmail(), passwordEncoder.encode(userRegisterDto.getPassword()), UserRole.CUSTOMER);
        user.setStatus(UserStatus.CREATED);
        return userRepository.save(user);
    }
}
