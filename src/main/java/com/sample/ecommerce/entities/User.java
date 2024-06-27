package com.sample.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    @ToString.Exclude
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    @Setter
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Address> addresses;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @ToString.Exclude
    @Setter
    private Cart cart;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Order> orders;

    public User(String name, String email, String password, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !UserStatus.INACTIVE.equals(status);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatus.INACTIVE.equals(status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !UserStatus.INACTIVE.equals(status);
    }

    @Override
    public boolean isEnabled() {
        return !UserStatus.INACTIVE.equals(status);
    }
}
