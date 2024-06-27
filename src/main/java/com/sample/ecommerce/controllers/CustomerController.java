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
import com.sample.ecommerce.helpers.AppConstants;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("add-address")
    public Response<Address> addAddress(@RequestBody AddressDto addressDto) {
        try {
            return Response.<Address>builder().data(customerService.addAddress(addressDto)).status(Response.Status.SUCCESS).build();
        } catch (UserException e) {
            return Response.<Address>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Adding Address", e);
            return Response.<Address>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @PostMapping("create-update-cart")
    public Response<Cart> createOrUpdateCart(@RequestBody CartDto cartDto) {
        try {
            return Response.<Cart>builder().data(customerService.createOrUpdateCart(cartDto)).status(Response.Status.SUCCESS).build();
        } catch (CartException | ProductException e) {
            return Response.<Cart>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Updating Cart", e);
            return Response.<Cart>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @GetMapping("create-order/{shipping-address-id}")
    public Response<Order> createOrder(@PathVariable("shipping-address-id") long shippingAddressId) {
        try {
            return Response.<Order>builder().data(customerService.createOrder(shippingAddressId)).status(Response.Status.SUCCESS).build();
        } catch (CartException | UserException e) {
            return Response.<Order>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Creating Order", e);
            return Response.<Order>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @GetMapping("cancel-order/{id}")
    public Response<Order> cancelOrder(@PathVariable("id") Long orderId) {
        try {
            return Response.<Order>builder().data(customerService.cancelOrder(orderId)).status(Response.Status.SUCCESS).build();
        } catch (OrderException e) {
            return Response.<Order>builder().status(Response.Status.FAILURE).message(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error Cancelling Order", e);
            return Response.<Order>builder().status(Response.Status.ERROR).message(AppConstants.ERROR_MESSAGE).build();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Customer From Protected Route";
    }
}
