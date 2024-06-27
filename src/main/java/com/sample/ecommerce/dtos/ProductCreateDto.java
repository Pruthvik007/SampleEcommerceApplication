package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class ProductCreateDto {
    private String name;
    private String description;
    private Double price;
    private String image;
    private Set<Long> categoryIds;
}
