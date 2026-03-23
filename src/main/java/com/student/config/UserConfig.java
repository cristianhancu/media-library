package com.student.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                        
                        .requestMatchers("/users/all").permitAll()
                        .requestMatchers("/users/addUser").hasRole("ADMIN").anyRequest().authenticated()
                       ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

     @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService customService,
                                                 PasswordEncoder passwordEncoder) {

        UserDetails admin = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager inMemory =
                new InMemoryUserDetailsManager(admin);

        return username -> {
            try {
                return customService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                return inMemory.loadUserByUsername(username);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}