package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepositories extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByIdNum(String idNum);
}
