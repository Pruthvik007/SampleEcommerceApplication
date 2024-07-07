package com.sample.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AddressDto {
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    @NotBlank(message = "Title is Required")
    private String title;

    @NotBlank(message = "Street Is Required")
    @Size(min = 20, max = 200, message = "Street must be between 20 and 200 characters")
    private String street;

    @NotBlank(message = "City Is Required")
    @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
    private String city;

    @NotBlank(message = "State Is Required")
    @Size(min = 3, max = 50, message = "State must be between 3 and 50 characters")
    private String state;

    @NotBlank(message = "Country Is Required")
    @Size(min = 3, max = 50, message = "Country must be between 3 and 50 characters")
    private String country;

    @NotBlank(message = "ZipCode Is Required")
    @Size(min = 6, max = 6, message = "ZipCode must be exactly 6 characters")
    private String zip;
}
