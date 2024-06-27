package com.sample.ecommerce.services;

import com.sample.ecommerce.dtos.AddressDto;
import com.sample.ecommerce.dtos.CartDto;
import com.sample.ecommerce.entities.Address;
import com.sample.ecommerce.entities.Cart;
import com.sample.ecommerce.entities.Order;
import com.sample.ecommerce.exceptions.CartException;
import com.sample.ecommerce.exceptions.OrderException;
import com.sample.ecommerce.exceptions.ProductException;
import com.sample.ecommerce.exceptions.UserException;

public interface CustomerService {
    Address addAddress(AddressDto addressDto) throws UserException;

    Cart createOrUpdateCart(CartDto cartDto) throws ProductException, CartException;

    Order createOrder(Long shippingAddressId) throws CartException, UserException;

    Order cancelOrder(Long orderId) throws OrderException;
}
