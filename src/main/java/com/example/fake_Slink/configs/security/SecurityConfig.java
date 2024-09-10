package com.example.fake_Slink.configs.security;

import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.Teacher;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final StudentRepository studentRepositories;
    private final TeacherRepository teacherRepositories;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return idNum -> {
            Optional<Student> student = studentRepositories.findByIdNum(idNum);
            if (student.isPresent()) {
                return student.get();
            }
            Optional<Teacher> teacher = teacherRepositories.findByIdNum(idNum);
            if (teacher.isPresent()) {
                return teacher.get();
            }
            throw new UsernameNotFoundException("Không tìm thấy sinh viên hoặc giáo viên!");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
