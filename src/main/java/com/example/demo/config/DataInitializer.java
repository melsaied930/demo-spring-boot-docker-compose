package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void initData() {
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ADMIN").build()));

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));

        System.out.println("✅ Roles initialized.");

        userRepository.findByUsername("admin").orElseGet(() -> {
            User admin = User.builder()
                    .username("admin")
                    .password("admin")
                    .roles(Set.of(adminRole, userRole))
                    .build();
            return userRepository.save(admin);
        });

        System.out.println("✅ Admin user initialized.");

        userRepository.findByUsername("user").orElseGet(() -> {
            User user = User.builder()
                    .username("user")
                    .password("user")
                    .roles(Set.of(userRole))
                    .build();
            return userRepository.save(user);
        });

        System.out.println("✅ Normal user initialized.");
    }
}
