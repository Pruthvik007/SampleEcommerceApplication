package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CategoryUpdateDto extends CategoryCreateDto {
    private Long id;
}
