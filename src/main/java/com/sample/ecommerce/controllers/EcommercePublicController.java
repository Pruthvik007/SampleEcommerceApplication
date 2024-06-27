package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.UserLoginDto;
import com.sample.ecommerce.dtos.UserRegisterDto;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.AppConstants;
import com.sample.ecommerce.helpers.JwtHelper;
import com.sample.ecommerce.pojos.PageResponse;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.EcommerceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
@RequiredArgsConstructor
public class EcommercePublicController {
    private final EcommerceService ecommerceService;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @GetMapping("/products")
    public PageResponse<List<Product>> getProducts(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                                   @RequestParam(name = "categoryId", required = false) Long categoryId,
                                                   @RequestParam(name = "page", required = false, defaultValue = "0") int pageNo) {
        try {
            Page<Product> page = ecommerceService.searchProducts(categoryId, searchTerm, pageNo);
            return PageResponse.<List<Product>>builder().data(page.getContent()).totalPages(page.getTotalPages()).pageNo(pageNo).status(Response.Status.SUCCESS).build();
        } catch (Exception e) {
            log.error("Error Fetching Products", e);
            return PageResponse.<List<Product>>builder().message(AppConstants.ERROR_MESSAGE).status(Response.Status.ERROR).build();
        }
    }

    @PostMapping("/register")
    public Response<User> signup(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            return Response.<User>builder().data(ecommerceService.registerCustomer(userRegisterDto)).status(Response.Status.SUCCESS).build();
        } catch (UserException e) {
            return Response.<User>builder().message(e.getMessage()).status(Response.Status.FAILURE).build();
        } catch (Exception e) {
            log.error("Error Registering Customer", e);
            return Response.<User>builder().message(AppConstants.ERROR_MESSAGE).status(Response.Status.ERROR).build();
        }
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );
            return Response.<String>builder().data(jwtHelper.generateToken(authentication.getName())).status(Response.Status.SUCCESS).build();
        } catch (BadCredentialsException e) {
            return Response.<String>builder().message(e.getMessage()).status(Response.Status.FAILURE).build();
        } catch (Exception e) {
            log.error("Error Logging In", e);
            return Response.<String>builder().message(AppConstants.ERROR_MESSAGE).status(Response.Status.ERROR).build();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello From Public Route";
    }
}
