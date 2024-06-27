package com.sample.ecommerce.components;

import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.entities.UserRole;
import com.sample.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminInitializer.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin-name}")
    private String adminName;
    @Value("${admin-email}")
    private String adminEmail;
    @Value("${admin-password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        try {
            if (!userRepository.existsByUserRole(UserRole.ADMIN)) {
                User admin = new User(adminName, adminEmail, passwordEncoder.encode(adminPassword), UserRole.ADMIN);
                userRepository.save(admin);
                log.info("Admin Created");
            }
        } catch (Exception e) {
            log.error("Error Creating Admin : ", e);
        }
    }
}
