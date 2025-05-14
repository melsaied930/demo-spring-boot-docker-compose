package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/user", "/user/**").hasRole("tenant-1-role-user")
                            .requestMatchers("/admin", "/admin/**").hasRole("tenant-1-role-admin")
                            .anyRequest().authenticated();
                })
                .oauth2ResourceServer(resource -> {
                    resource
                            .jwt(jwt -> jwt
                                    .jwtAuthenticationConverter(jwtAuthenticationConverter()));
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling
                            .authenticationEntryPoint((request, response, authException) -> {
                                response.setStatus(401);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"error\":\"Unauthorized\",\"message\":\"" +
                                        authException.getMessage() + "\"}");
                            })
                            .accessDeniedHandler((request, response, accessDeniedException) -> {
                                response.setStatus(403);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"error\":\"Forbidden\",\"message\":\"" +
                                        accessDeniedException.getMessage() + "\"}");
                            });
                });
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Map<String, Object> access = jwt.getClaim("realm_access");
            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) access.get("roles");
            return roles.stream().map(role -> {
                String authority = "ROLE_" + role;
                return new SimpleGrantedAuthority(authority);
            }).collect(Collectors.toList());
        });
        return jwtConverter;
    }
}