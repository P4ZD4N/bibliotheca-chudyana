package com.p4zd4n.bibliothecachudyana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/books", "/books/{id}", "/find-books", "/top-categories", "/discounts", "/new-releases", "/register", "/error", "/css/**", "/js/**").permitAll()
                .requestMatchers("/add-book", "/save-book", "/update-book", "/delete-book").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/user/{username}/cart", "/add-to-cart", "/remove-from-cart").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/add-discount", "/save-discount", "/update-discount", "/delete-discount").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/order-form", "/order-confirmation", "/order").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/orders-management", "/orders-management/find-orders", "/orders-management/update-order", "/orders-management/save-order", "/orders-management/delete-order").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/manager").hasAnyRole( "MANAGER", "ADMIN")
                .requestMatchers("/employee").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/reviews-management", "/reviews-management/find-review", "/reviews-management/update-review", "/reviews-management/delete-review").hasAnyRole( "MANAGER", "ADMIN")
                .requestMatchers("/add-review-for-{bookId}", "/reviews-management/save-review").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/user/{username}", "/user/{username}/change-email", "/user/{username}/change-password").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers("/users-management", "/users-management/add-user", "/users-management/save-user", "/users-management/find-users", "/users-management/update-user", "/users-management/delete-user").hasAnyRole( "ADMIN")
                .requestMatchers("/user/{username}/wishlist", "/add-to-wishlist", "/remove-from-wishlist").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                .anyRequest().authenticated()
        )
        .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/")
                .permitAll()
        )
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        );

        return http.build();
    }
}
