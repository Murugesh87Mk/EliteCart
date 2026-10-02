package com.example.e_commerceWebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.e_commerceWebsite.entity")
@EnableJpaRepositories(basePackages = "com.example.e_commerceWebsite.repository")
public class ECommerceWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceWebsiteApplication.class, args);
    }
}