package com.sample.ecommerce.repositories;

import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByUserRole(UserRole userRole);
}
