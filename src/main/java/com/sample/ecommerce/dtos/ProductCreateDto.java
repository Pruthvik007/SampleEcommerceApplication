package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {

    @NotBlank(message = "Product Name is Required")
    @Size(min = 3, max = 20, message = "Name of Product must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Product Description is Required")
    @Size(min = 20, max = 200, message = "Product Description must be between 20 and 200 characters")
    private String description;

    @NotNull(message = "Product Price is Required")
    private Double price;

    @NotBlank(message = "Product Image is Required")
    private String image;

    @NotEmpty(message = "At least 1 Category is Required")
    private Set<Long> categoryIds;
}
