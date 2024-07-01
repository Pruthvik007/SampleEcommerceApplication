package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CategoryUpdateDto extends CategoryCreateDto {

    @NotNull(message = "Category Id is Required")
    private Long id;

    public CategoryUpdateDto(String name, String description, Long id) {
        super(name, description);
        this.id = id;
    }
}
