package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class CartDto {
    private Map<Long, Integer> cartItems;
}
