package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;
import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.Semester;
import com.example.fake_Slink.models.Subject;
import com.example.fake_Slink.models.Teacher;
import com.example.fake_Slink.repositories.*;
import com.example.fake_Slink.services.ClassSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassSubjectServiceImpl implements ClassSubjectService {

    private final ClassSubjectRepository classSubjectRepository;
    private final SemesterRepository semesterRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public boolean createClassSubjects(List<CreateClassSubjectRequest> list) throws Exception {
        for (var x : list) {
            Semester semester = semesterRepository.findById(x.getSemesterID()).orElseThrow(
                    () -> new Exception("Khong tim thay semester voi id: " + x.getSemesterID())
            );
            Teacher teacher = teacherRepository.findByIdNum(x.getTeacherIDNum()).orElseThrow(
                    () -> new Exception("Khong tim thay teacher voi idNum: " + x.getTeacherIDNum())
            );
            Subject subject = subjectRepository.findByIdNum(x.getSubjectIDNum()).orElseThrow(
                    () -> new Exception("Khong tim thay subject voi idNum: " + x.getSubjectIDNum())
            );

            ClassSubject newClassSubject = ClassSubject.fromCreateClassSubjectRequest(
                    x,
                    semester,
                    teacher,
                    subject
            );

            classSubjectRepository.save(newClassSubject);
        }
        return true;
    }

    @Override
    public ClassSubjectResponse getClassSubject(int classSubjectID) throws Exception {
        ClassSubject classSubject = classSubjectRepository.findById(classSubjectID).orElseThrow(
                () -> new Exception("Khong tim thay classSubject voi id: " + classSubjectID)
        );
        return ClassSubjectResponse.fromClassSubject(classSubject);
    }
}
