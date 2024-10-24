package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Grade;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.embeddable.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    List<Grade> findByStudent(Student student);
}
