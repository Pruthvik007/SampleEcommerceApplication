package com.sample.ecommerce.helpers;

import com.sample.ecommerce.dtos.CartDto;

import java.util.Map;

public class BeanValidator {
    private BeanValidator() {
    }

    public static String isValidCartDetails(CartDto cartDto) {
        if (Validator.isEmpty(cartDto))
            return "Cart Details Are Required";
        if (Validator.isEmpty(cartDto.getCartItems()))
            return null;
        for (Map.Entry<Long, Integer> item : cartDto.getCartItems().entrySet()) {
            if (Validator.isEmpty(item.getKey()) || Validator.isEmpty(item.getValue())) {
                return "Invalid Cart Items";
            }
        }
        return null;
    }
}
