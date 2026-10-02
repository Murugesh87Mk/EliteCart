package com.example.e_commerceWebsite.config;

import com.example.e_commerceWebsite.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(
                    org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers("/super-admin/**").hasRole("SUPER_ADMIN")
                .requestMatchers("/admin/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .requestMatchers("/intermediate/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "INTERMEDIATE")
                .requestMatchers("/customer/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "INTERMEDIATE", "CUSTOMER")
                .requestMatchers(HttpMethod.POST, "/products/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "INTERMEDIATE")
                .requestMatchers(HttpMethod.PUT, "/products/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "INTERMEDIATE")
                .requestMatchers(HttpMethod.DELETE, "/products/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN")
                .requestMatchers("/orders/*/status")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "INTERMEDIATE")
                .requestMatchers("/users/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN")
                .requestMatchers("/orders/**", "/cart/**", "/wishlist/**")
                    .authenticated()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "http://localhost:5174",
                "http://localhost:5175",
                "http://localhost:5176"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
