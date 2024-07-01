package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class ProductUpdateDto extends ProductCreateDto {

    @NotNull(message = "Product Id is Required")
    private Long id;

    public ProductUpdateDto(String name, String description, Double price, String image, Set<Long> categoryIds, Long id) {
        super(name, description, price, image, categoryIds);
        this.id = id;
    }
}
