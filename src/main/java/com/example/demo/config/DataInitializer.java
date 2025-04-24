package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        // Create roles
        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        // Create users
        User admin = User.builder()
                .username("admin")
                .password("admin") // In production, use encoded password
                .roles(Set.of(adminRole, userRole))
                .build();

        User user = User.builder()
                .username("user")
                .password("user")
                .roles(Set.of(userRole))
                .build();

        userRepository.save(admin);
        userRepository.save(user);

        System.out.println("✅ Sample data initialized.");
    }
}
