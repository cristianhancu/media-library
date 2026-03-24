package com.student.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;

@Configuration
public class UserConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .authorizeHttpRequests(req -> req
            .requestMatchers("/users/addUser").hasRole("ADMIN")
            .requestMatchers("/users/all").permitAll()
            .requestMatchers("/users/delete/all").hasRole("ADMIN")
            .requestMatchers("/users/id/{id}").permitAll()
            .requestMatchers("/users/username/{username}").permitAll()
            .requestMatchers("/users/email/{email}").permitAll()
            .requestMatchers("/users/update/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/users/deleteid/{id}").hasRole("ADMIN")
            .requestMatchers("/users/deleteem/{email}").hasRole("ADMIN" )
            .requestMatchers("/users/deleteusername/{username}").hasRole("ADMIN")
            .anyRequest().authenticated()           
        )
        .httpBasic(Customizer.withDefaults());

    return http.build();
}   


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}