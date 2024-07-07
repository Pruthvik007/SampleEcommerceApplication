package com.sample.ecommerce.dtos;

import lombok.*;

import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartDto {
    private Map<Long, Integer> cartItems;
}
