package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Grade;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.embeddable.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    List<Grade> findByStudent(Student student);
    @Query("SELECT g FROM Grade g WHERE g.student = :student AND g.appealsDateline >= CURRENT_DATE")
    List<Grade> findByStudentAndAppealsDatelineAfterToday(@Param("student") Student student);
}
