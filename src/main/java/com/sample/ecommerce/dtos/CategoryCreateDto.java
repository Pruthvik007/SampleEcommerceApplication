package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {

    @NotBlank(message = "Category Name is Required")
    @Size(min = 3, max = 20, message = "Name of Category must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Category Description is Required")
    @Size(min = 20, max = 200, message = "Category Description must be between 20 and 200 characters")
    private String description;
}
