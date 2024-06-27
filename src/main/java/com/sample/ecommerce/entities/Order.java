package com.sample.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    @ToString.Exclude
    private User user;
    @ManyToOne
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "address_id", nullable = false)
    private Address shippingAddress;
    @Enumerated(EnumType.STRING)
    @Setter
    private OrderStatus status;

    public Order(User user, List<OrderItem> orderItems, Double totalPrice, Address shippingAddress, OrderStatus status) {
        this.user = user;
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(this);
        }
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.status = status;
    }
}
