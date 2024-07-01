package com.sample.ecommerce.controllers;

import com.sample.ecommerce.dtos.AddressDto;
import com.sample.ecommerce.dtos.CartDto;
import com.sample.ecommerce.entities.Address;
import com.sample.ecommerce.entities.Cart;
import com.sample.ecommerce.entities.Order;
import com.sample.ecommerce.exceptions.CartException;
import com.sample.ecommerce.exceptions.OrderException;
import com.sample.ecommerce.exceptions.ProductException;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("add-address")
    public Response<Address> addAddress(@RequestBody @Valid AddressDto addressDto) throws UserException {
        return Response.<Address>builder().data(customerService.addAddress(addressDto)).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("create-update-cart")
    public Response<Cart> createOrUpdateCart(@RequestBody CartDto cartDto) throws CartException, ProductException {
        return Response.<Cart>builder().data(customerService.createOrUpdateCart(cartDto)).status(Response.Status.SUCCESS).build();
    }

    @GetMapping("create-order/{shipping-address-id}")
    public Response<Order> createOrder(@PathVariable("shipping-address-id") long shippingAddressId) throws CartException, UserException {
        return Response.<Order>builder().data(customerService.createOrder(shippingAddressId)).status(Response.Status.SUCCESS).build();
    }

    @GetMapping("cancel-order/{id}")
    public Response<Order> cancelOrder(@PathVariable("id") Long orderId) throws OrderException {
        return Response.<Order>builder().data(customerService.cancelOrder(orderId)).status(Response.Status.SUCCESS).build();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Customer From Protected Route";
    }
}
