package com.example.fake_Slink.configs.security;

import com.example.fake_Slink.configs.security.filters.JwtFilter;
import com.example.fake_Slink.enums.Role;
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
                           .requestMatchers(
                                   HttpMethod.POST,
                                   String.format("%s/students/student_authentication", apiPrefix),
                                   String.format("%s/teachers/teacher_authentication", apiPrefix)
                           ).permitAll()
                           .requestMatchers(
                                   HttpMethod.POST,
                                   String.format("%s/teachers/add_teacher", apiPrefix),
                                   String.format("%s/teachers/add_admin", apiPrefix),
                                   String.format("%s/students/add_students", apiPrefix),
                                   String.format("%s/students/get_student_list", apiPrefix),
                                   String.format("%s/subjects/add_subjects", apiPrefix),
                                   String.format("%s/departments/add_departments", apiPrefix),
                                   String.format("%s/majors/add_majors", apiPrefix),
                                   String.format("%s/semester", apiPrefix),
                                   String.format("%s/classSubject", apiPrefix),
                                   String.format("%s/timeTables", apiPrefix),
                                   String.format("%s/subject_registration", apiPrefix)
                           ).hasAnyRole(Role.ADMIN.toString())
                           .requestMatchers(
                                   HttpMethod.GET,
                                   String.format("%s/students/student_detail", apiPrefix),
                                   String.format("%s/semester", apiPrefix),
                                   String.format("%s/classSubject", apiPrefix),
                                   String.format("%s/classSubject/credit_class", apiPrefix),
                                   String.format("%s/classSubject/get_students_in_credit_class", apiPrefix),
                                   String.format("%s/timeTables", apiPrefix),
                                   String.format("%s/grade", apiPrefix)
                           ).hasAnyRole(Role.STUDENT.toString())
                           .requestMatchers(
                                   HttpMethod.PUT,
                                   String.format("%s/students/update_student_detail", apiPrefix)
                           ).hasAnyRole(Role.STUDENT.toString())
                           .requestMatchers(
                                   HttpMethod.PUT,
                                   String.format("%s/grade", apiPrefix)
                           ).hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString())
                           .requestMatchers(
                                   HttpMethod.PATCH,
                                   String.format("%s/students/update_password", apiPrefix),
                                   String.format("%s/students/upload_avatar_url", apiPrefix)
                           ).hasAnyRole(Role.STUDENT.toString())
                           .requestMatchers(
                                   HttpMethod.GET,
                                   String.format("%s/semester", apiPrefix),
                                   String.format("%s/classSubject", apiPrefix)
                           ).hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString())
                           .anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
}
