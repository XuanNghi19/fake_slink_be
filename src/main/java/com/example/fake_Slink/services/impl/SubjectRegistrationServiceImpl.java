package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateSubjectRegistrationRequest;
import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.Grade;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.SubjectRegistration;
import com.example.fake_Slink.repositories.ClassSubjectRepository;
import com.example.fake_Slink.repositories.GradeRepository;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.repositories.SubjectRegistrationRepository;
import com.example.fake_Slink.services.SubjectRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectRegistrationServiceImpl implements SubjectRegistrationService {
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final StudentRepository studentRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final GradeRepository gradeRepository;

    @Override
    public boolean createSubjectRegistration(List<CreateSubjectRegistrationRequest> list) throws Exception {
        for (var x : list) {
            Student student = studentRepository.findByIdNum(x.getStudentIdNum()).orElseThrow(
                    () -> new Exception("Khong tim thay student voi idNum: " + x.getStudentIdNum())
            );
            ClassSubject classSubject = classSubjectRepository.findById(x.getClassSubjectId()).orElseThrow(
                    () -> new Exception("Khong tim thay classSubject voi classSubjectId: " + x.getClassSubjectId())
            );

            Grade grade = Grade.fromSubjectRegistration(student, classSubject);
            gradeRepository.save(grade);

            SubjectRegistration subjectRegistration = SubjectRegistration.fromCreateSubjectRegistrationRequest(student, classSubject);
            subjectRegistrationRepository.save(subjectRegistration);
        }
        return true;
    }
}