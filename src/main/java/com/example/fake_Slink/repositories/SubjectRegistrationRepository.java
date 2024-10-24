package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.SubjectRegistration;
import com.example.fake_Slink.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Integer> {
    List<SubjectRegistration> findByStudent(Student student);
    List<SubjectRegistration> findByStudentAndClassSubjectSemesterEndDate(Student student, Date today);
}
