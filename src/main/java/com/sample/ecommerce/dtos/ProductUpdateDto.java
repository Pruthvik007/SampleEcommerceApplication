package com.sample.ecommerce.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductUpdateDto extends ProductCreateDto {
    private Long id;
}
