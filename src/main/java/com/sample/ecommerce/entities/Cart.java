package com.sample.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    @ToString.Exclude
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;
    private Double totalValue;

    public Cart(Long id, User user, List<CartItem> cartItems, Double totalValue) {
        this.id = id;
        this.user = user;
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        for (CartItem cartItem : cartItems) {
            cartItem.setCart(this);
        }
        this.cartItems = cartItems;
        this.totalValue = totalValue;
    }
}
