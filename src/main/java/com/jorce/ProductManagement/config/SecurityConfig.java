package com.jorce.ProductManagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/cart", "/products/getProducts","/address")
                        .hasAnyRole("ADMIN", "USER", "SUPER_ADMIN")
                        .requestMatchers("/products/addProduct", "/products/updateProduct/*", "/products/*/price-quantity", "/products/assignCategory", "/products/*/visibility")
                        .hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/user/addUser", "/user/updateUser", "/user/*/updateRole", "/user/deleteUser/*", "/categories/**")
                        .hasRole("SUPER_ADMIN")
                        .anyRequest().authenticated()
                );
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}