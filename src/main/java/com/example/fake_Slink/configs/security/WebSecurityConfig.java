package com.example.fake_Slink.configs.security;

import com.example.fake_Slink.configs.security.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtFilter jwtFilter;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
                   request
                           .requestMatchers(
                                   HttpMethod.POST,
                                   String.format("%s/teachers/**", apiPrefix)
                           ).permitAll()
                           .requestMatchers(
                                   "/api-docs",
                                   "/api-docs/**",
                                   "/swagger-resources",
                                   "/swagger-resources/**",
                                   "/configuration/ui",
                                   "/configuration/security",
                                   "/swagger-ui/**",
                                   "/swagger-ui.html",
                                   "/webjars/swagger-ui/**",
                                   "/swagger-ui/index.html")
                           .permitAll()
                           .anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
}
