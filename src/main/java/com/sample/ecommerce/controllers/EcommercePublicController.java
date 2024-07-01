package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.UserLoginDto;
import com.sample.ecommerce.dtos.UserRegisterDto;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.JwtHelper;
import com.sample.ecommerce.pojos.PageResponse;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.EcommerceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class EcommercePublicController {

    private final EcommerceService ecommerceService;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @GetMapping("/products")
    public PageResponse<List<Product>> getProducts(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                                   @RequestParam(name = "categoryId", required = false) Long categoryId,
                                                   @RequestParam(name = "page", required = false, defaultValue = "0") int pageNo) {
        Page<Product> page = ecommerceService.searchProducts(categoryId, searchTerm, pageNo);
        return PageResponse.<List<Product>>builder().data(page.getContent()).totalPages(page.getTotalPages()).pageNo(pageNo).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("/register")
    public Response<User> registerCustomer(@RequestBody @Valid UserRegisterDto userRegisterDto) throws UserException {
        return Response.<User>builder().data(ecommerceService.registerCustomer(userRegisterDto)).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
        );
        return Response.<String>builder().data(jwtHelper.generateToken(authentication.getName())).status(Response.Status.SUCCESS).build();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello From Public Route";
    }
}
