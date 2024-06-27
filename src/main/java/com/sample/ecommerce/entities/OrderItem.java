package com.sample.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_items")
@Getter
@ToString
@NoArgsConstructor
public class OrderItem extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonIgnore
    @ToString.Exclude
    @Setter
    private Order order;

    public OrderItem(Product product, int quantity) {
        super(product, quantity);
    }
}
