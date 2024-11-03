package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer> {
        @Query("SELECT e FROM ExamSchedule e WHERE e.classSubject IN :classSubjects")
        List<ExamSchedule> findByClassSubjects(@Param("classSubjects") List<ClassSubject> classSubjects);
}
