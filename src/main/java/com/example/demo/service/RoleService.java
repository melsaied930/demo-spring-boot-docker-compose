package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public Role save(Role role) {
        repository.findByName(role.getName()).ifPresent(existing -> {
            if (!existing.getId().equals(role.getId())) { // Updating a different role
                throw new RuntimeException("Role name already exists: " + role.getName());
            }
        });
        return repository.save(role);
    }

    public void delete(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Remove role from users
        for (User user : role.getUsers()) {
            user.getRoles().remove(role);
        }

        repository.deleteById(id);
    }

    public List<Role> findAllById(List<Long> roleIds) {
        return repository.findAllById(roleIds);
    }
}
