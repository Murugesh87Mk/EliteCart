package com.example.e_commerceWebsite.config;

import com.example.e_commerceWebsite.entity.Product;
import com.example.e_commerceWebsite.entity.Role;
import com.example.e_commerceWebsite.entity.User;
import com.example.e_commerceWebsite.repository.ProductRepository;
import com.example.e_commerceWebsite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed-users:true}")
    private boolean seedUsers;

    @Value("${app.seed-products:true}")
    private boolean seedProducts;

    @Override
    public void run(String... args) {
        if (seedUsers) {
            createUser("superadmin@eshop.local", "Super Admin", "superadmin123", Role.SUPER_ADMIN);
            createUser("admin@eshop.local", "Admin", "admin123", Role.ADMIN);
            createUser("intermediate@eshop.local", "Intermediate", "intermediate123", Role.INTERMEDIATE);
        }

        if (seedProducts && productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .productName("iPhone 15")
                    .description("Apple smartphone")
                    .price(59999.0)
                    .quantity(20)
                    .category("Electronics")
                    .build());

            productRepository.save(Product.builder()
                    .productName("MacBook Pro")
                    .description("Apple laptop")
                    .price(130899.0)
                    .quantity(10)
                    .category("Electronics")
                    .build());

            productRepository.save(Product.builder()
                    .productName("Nike Shoes")
                    .description("Everyday sports shoes")
                    .price(2699.0)
                    .quantity(30)
                    .category("Fashion")
                    .build());

            productRepository.save(Product.builder()
                    .productName("Sony Headphones")
                    .description("Wireless headphones")
                    .price(5679.0)
                    .quantity(25)
                    .category("Electronics")
                    .build());
        }
    }

    private void createUser(String email, String name, String password, Role role) {
        if (userRepository.existsByEmail(email)) return;

        userRepository.save(User.builder()
                .fullName(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .enabled(true)
                .build());
    }
}
