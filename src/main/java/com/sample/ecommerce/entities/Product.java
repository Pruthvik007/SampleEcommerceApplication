package com.sample.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "product_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "category_id"
            )
    )
    private Set<Category> categories;

    public Product(String name, String description, Double price, String image, Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.categories = categories;
    }

    public Product updateDetails(String name, String description, Double price, String image, Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.categories = categories;
        return this;
    }
}
