package com.sample.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartItem extends Item {
    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @JsonIgnore
    @ToString.Exclude
    @Setter
    private Cart cart;

    public CartItem(Product product, int quantity) {
        super(product, quantity);
    }
}
