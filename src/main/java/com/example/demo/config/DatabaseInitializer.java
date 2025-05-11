package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Starting database initialization check");

        long roleCount = roleRepository.count();
        long userCount = userRepository.count();

        log.info("Current state: {} roles and {} users in database", roleCount, userCount);

        if (roleCount == 0 && userCount == 0) {
            initializeDatabase();
        } else {
            log.info("Database already contains data, skipping initialization");

            roleRepository.findAll().forEach(role ->
                    log.info("Existing role: {}", role.getName()));

            userRepository.findAll().forEach(user ->
                    log.info("Existing user: {} with roles: {}",
                            user.getUsername(),
                            user.getRoles().stream().map(Role::getName).toArray()));
        }
    }

    @Transactional
    private void initializeDatabase() {
        log.info("Initializing database with default roles and users");

        Role userRole = roleRepository.save(Role.builder().name("USER").build());
        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());

        log.info("Created roles: USER (id: {}), ADMIN (id: {})", userRole.getId(), adminRole.getId());

        String userPassword = passwordEncoder.encode("user");
        String adminPassword = passwordEncoder.encode("admin");

        log.info("Encoded passwords. User password length: {}, Admin password length: {}",
                userPassword.length(), adminPassword.length());

        User user = User.builder()
                .username("user")
                .password(userPassword)
                .enabled(true)
                .roles(Set.of(userRole))
                .build();

        User admin = User.builder()
                .username("admin")
                .password(adminPassword)
                .enabled(true)
                .roles(Set.of(adminRole, userRole))
                .build();

        userRepository.save(user);
        userRepository.save(admin);

        log.info("Created users: user (id: {} with {} roles), admin (id: {} with {} roles)",
                user.getId(), user.getRoles().size(),
                admin.getId(), admin.getRoles().size());

        log.info("Database initialization complete. Now contains {} roles and {} users",
                roleRepository.count(), userRepository.count());
    }
}
