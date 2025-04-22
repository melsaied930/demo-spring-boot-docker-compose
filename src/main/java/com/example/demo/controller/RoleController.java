package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Role get(@PathVariable UUID id) {
        return service.findById(id).orElseThrow();
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return service.save(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable UUID id, @RequestBody Role role) {
        role.setId(id);
        return service.save(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
