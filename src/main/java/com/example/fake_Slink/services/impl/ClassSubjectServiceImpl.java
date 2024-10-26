package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import com.example.fake_Slink.dtos.responses.*;
import com.example.fake_Slink.models.*;
import com.example.fake_Slink.repositories.*;
import com.example.fake_Slink.services.ClassSubjectService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.mutable.Mutable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassSubjectServiceImpl implements ClassSubjectService {

    private final ClassSubjectRepository classSubjectRepository;
    private final SemesterRepository semesterRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final SubjectRegistrationRepository subjectRegistrationRepository;

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

    @Override
    public CreditClassResponse getCreditClass(String idNum) throws Exception {
        Student student = studentRepository.findByIdNum(idNum)
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + idNum));
        List<ClassSubjectResponse> classSubjectResponseList = subjectRegistrationRepository.findByStudent(student)
                .stream()
                .map(subjectRegistration -> ClassSubjectResponse.fromClassSubject(subjectRegistration.getClassSubject()))
                .toList();

        List<SemesterResponse> semesterResponseList = new ArrayList<>();
        for (var x : classSubjectResponseList) {
            ClassSubjectResponse classSubjectResponse = x;
            if (!semesterResponseList.contains(classSubjectResponse.getSemesterResponse())) {
                semesterResponseList.add(classSubjectResponse.getSemesterResponse());
            }
        }

        return new CreditClassResponse(semesterResponseList, classSubjectResponseList);
    }

    @Override
    public List<StudentResponse> getStudentInCreditClass(int classSubjectId) throws Exception {
        ClassSubject classSubject = classSubjectRepository.findById(classSubjectId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay classSubject voi classSubjectId: " + classSubjectId));
        List<SubjectRegistration> subjectRegistrationList = subjectRegistrationRepository.findByClassSubject(classSubject);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        for(var x : subjectRegistrationList) {
            studentResponseList.add(StudentResponse.fromStudent(studentRepository.findById(x.getStudent().getId()).orElseThrow(
                    () -> new RuntimeException("Khong tim thay student voi id: " +  x.getStudent().getId())
            )));
        }
        return studentResponseList;
    }
}
