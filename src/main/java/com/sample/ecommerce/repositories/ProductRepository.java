package com.sample.ecommerce.repositories;

import com.sample.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN p.categories c " +
            "WHERE (:categoryId IS NULL OR c.id = :categoryId) " +
            "AND (:searchTerm IS NULL OR :searchTerm = '' OR p.name LIKE %:searchTerm%)")
    Page<Product> findProductsByCategoryIdAndSearchTerm(@Param("categoryId") Long categoryId,
                                                        @Param("searchTerm") String searchTerm, Pageable pageable);
}
