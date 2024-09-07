package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepositories extends JpaRepository<Student, Integer> {
    Optional<Student> findByIdNum(String idNum);
    Boolean existsByIdNum(String idNum);
}
