package com.sample.ecommerce.helpers;

import com.sample.ecommerce.dtos.*;

import java.util.Map;

public class BeanValidator {
    private BeanValidator() {
    }

    public static String isValidAddressDetails(AddressDto addressDto) {
        if (Validator.isEmpty(addressDto))
            return "Address Details Are Required";
        if (Validator.isEmpty(addressDto.getTitle()))
            return "Title Is Required";
        if (Validator.isEmpty(addressDto.getAddress()))
            return "Address Is Required";
        if (Validator.isEmpty(addressDto.getCity()))
            return "City Is Required";
        if (Validator.isEmpty(addressDto.getState()))
            return "State Is Required";
        if (Validator.isEmpty(addressDto.getZip()))
            return "Zip Is Required";
        if (Validator.isEmpty(addressDto.getCountry()))
            return "Country Is Required";
        return null;
    }

    public static String isValidCartDetails(CartDto cartDto) {
        if (Validator.isEmpty(cartDto))
            return "Cart Details Are Required";
        if (Validator.isEmpty(cartDto.getCartItems()))
            return null;
        for (Map.Entry<Long, Integer> item : cartDto.getCartItems().entrySet()) {
            if (Validator.isEmpty(item.getKey()) || Validator.isEmpty(item.getValue())) {
                return "Invalid Cart Items";
            }
        }
        return null;
    }

    public static String isValidEmployeeDetails(EmployeeRegisterDto employeeRegisterDto) {
        if (Validator.isEmpty(employeeRegisterDto))
            return "User Details Are Required";
        if (Validator.isEmpty(employeeRegisterDto.getUserRole()))
            return "User Role Is Required";
        return isValidUserDetails(employeeRegisterDto);
    }

    public static String isValidUserDetails(UserRegisterDto userRegisterDto) {
        if (Validator.isEmpty(userRegisterDto))
            return "User Details Are Required";
        if (Validator.isEmpty(userRegisterDto.getName()))
            return "User Name Is Required";
        if (Validator.isEmpty(userRegisterDto.getEmail()))
            return "User Email Is Required";
        if (Validator.isEmpty(userRegisterDto.getPassword()))
            return "Password Is Required";
        if (!Validator.isEmailValid(userRegisterDto.getEmail()))
            return "Invalid User Email";
        return null;
    }

    public static String isValidProductDetails(ProductUpdateDto productUpdateDto) {
        if (Validator.isEmpty(productUpdateDto))
            return "Product Details Are Required";
        if (Validator.isEmpty(productUpdateDto.getId()))
            return "Product Id Is Required";
        return isValidProductDetails((ProductCreateDto) productUpdateDto);
    }

    public static String isValidProductDetails(ProductCreateDto productCreateDto) {
        if (Validator.isEmpty(productCreateDto))
            return "Product Details Are Required";
        if (Validator.isEmpty(productCreateDto.getName()))
            return "Product Name Is Required";
        if (Validator.isEmpty(productCreateDto.getPrice()))
            return "Product Price Is Required";
        if (Validator.isEmpty(productCreateDto.getDescription()))
            return "Product Description Is Required";
        if (Validator.isEmpty(productCreateDto.getImage()))
            return "Product Image Is Required";
        if (!Validator.isEmpty(productCreateDto.getCategoryIds())) {
            for (Long categoryId : productCreateDto.getCategoryIds()) {
                if (Validator.isEmpty(categoryId))
                    return "Invalid Category Is Passed";
            }
        }
        return null;
    }

    public static String isValidCategoryDetails(CategoryUpdateDto categoryUpdateDto) {
        if (Validator.isEmpty(categoryUpdateDto))
            return "Category Details Are Required";
        if (Validator.isEmpty(categoryUpdateDto.getId()))
            return "Category Id Is Required";
        return isValidCategoryDetails((CategoryCreateDto) categoryUpdateDto);
    }

    public static String isValidCategoryDetails(CategoryCreateDto categoryCreateDto) {
        if (Validator.isEmpty(categoryCreateDto))
            return "Category Details Are Required";
        if (Validator.isEmpty(categoryCreateDto.getName()))
            return "Category Name Is Required";
        if (Validator.isEmpty(categoryCreateDto.getDescription()))
            return "Category Description Is Required";
        return null;
    }
}
