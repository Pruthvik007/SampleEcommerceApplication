package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryCreateDto {
    private String name;
    private String description;
}
