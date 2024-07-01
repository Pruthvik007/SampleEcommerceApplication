package com.sample.ecommerce.services.impl;

import com.sample.ecommerce.dtos.CategoryCreateDto;
import com.sample.ecommerce.dtos.CategoryUpdateDto;
import com.sample.ecommerce.dtos.ProductCreateDto;
import com.sample.ecommerce.dtos.ProductUpdateDto;
import com.sample.ecommerce.entities.Category;
import com.sample.ecommerce.entities.Product;
import com.sample.ecommerce.exceptions.CategoryException;
import com.sample.ecommerce.exceptions.ProductException;
import com.sample.ecommerce.repositories.CategoryRepository;
import com.sample.ecommerce.repositories.ProductRepository;
import com.sample.ecommerce.services.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductCreateDto productCreateDto) throws CategoryException {
        Set<Category> categories = validateCategories(productCreateDto.getCategoryIds());
        return productRepository.save(new Product(productCreateDto.getName(), productCreateDto.getDescription(), productCreateDto.getPrice(), productCreateDto.getImage(), categories));
    }

    @Override
    public Product updateProduct(ProductUpdateDto productUpdateDto) throws ProductException, CategoryException {
        Product product = validateProduct(productUpdateDto.getId());
        Set<Category> categories = validateCategories(productUpdateDto.getCategoryIds());
        return productRepository.save(product.updateDetails(productUpdateDto.getName(), productUpdateDto.getDescription(), productUpdateDto.getPrice(), productUpdateDto.getImage(), categories));
    }

    @Override
    public Category createCategory(CategoryCreateDto categoryCreateDto) {
        return categoryRepository.save(new Category(categoryCreateDto.getName(), categoryCreateDto.getDescription()));
    }

    @Override
    public Category updateCategory(CategoryUpdateDto categoryUpdateDto) throws CategoryException {
        Category category = validateCategories(List.of(categoryUpdateDto.getId())).stream().findFirst().orElseThrow(() -> new CategoryException("Category Not Found"));
        return categoryRepository.save(category.updateDetails(categoryUpdateDto.getName(), categoryUpdateDto.getDescription()));
    }

    private Product validateProduct(Long productId) throws ProductException {
        return productRepository.findById(productId).orElseThrow(() -> new ProductException("Product Not Found"));
    }

    private Set<Category> validateCategories(Collection<Long> categoryIds) throws CategoryException {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : categoryIds) {
            if (categoryId != null) {
                Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryException("Category Not Found"));
                categories.add(category);
            }
        }
        return categories;
    }
}
