package com.student.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;

import com.student.model.RoleType;
import com.student.model.User;
import com.student.repository.UserRepository;

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
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
    return args -> {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(RoleType.ROLE_ADMIN);

            userRepository.save(admin);
        }
    };
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}