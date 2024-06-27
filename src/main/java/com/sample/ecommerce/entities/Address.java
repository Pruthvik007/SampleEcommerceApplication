package com.sample.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "addresses")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    private String title;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    @ToString.Exclude
    @Setter
    private User user;
    @OneToMany(mappedBy = "shippingAddress")
    @JsonIgnore
    @ToString.Exclude
    private Set<Order> orders;

    public Address(String title, String street, String city, String state, String country, String zip) {
        this.title = title;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }
}
