package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        // Create roles
        Role adminRole = Role.builder().name("ROLE_ADMIN").build();
        Role userRole = Role.builder().name("ROLE_USER").build();

        roleRepository.saveAll(List.of(adminRole, userRole));

        // Create users
        User admin = User.builder()
                .username("admin")
                .password("admin") // In production, encode this!
                .roles(Set.of(adminRole, userRole))
                .build();

        User normalUser = User.builder()
                .username("user")
                .password("user")
                .roles(Set.of(userRole))
                .build();

        userRepository.saveAll(List.of(admin, normalUser));

        System.out.println("✅ Sample roles and users initialized.");
    }
}
