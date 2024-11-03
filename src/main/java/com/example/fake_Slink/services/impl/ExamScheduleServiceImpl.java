package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateExamScheduleRequest;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;
import com.example.fake_Slink.dtos.responses.ExamScheduleResponse;
import com.example.fake_Slink.dtos.responses.SemesterResponse;
import com.example.fake_Slink.dtos.responses.ViewExamScheduleResponse;
import com.example.fake_Slink.models.*;
import com.example.fake_Slink.repositories.ClassSubjectRepository;
import com.example.fake_Slink.repositories.ExamScheduleRepository;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.repositories.SubjectRegistrationRepository;
import com.example.fake_Slink.services.ExamScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamScheduleServiceImpl implements ExamScheduleService {
    private final ExamScheduleRepository examScheduleRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final StudentRepository studentRepository;
    @Override
    public boolean createExamSchedule(List<CreateExamScheduleRequest> requests) {
        for(var it : requests) {
            ClassSubject classSubject;
            try{
                classSubject = classSubjectRepository.findById(it.getClassSubjectID())
                        .orElseThrow(() -> new RuntimeException("Khong the tim thay classSubject voi id: " + it.getClassSubjectID()));
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                return false;
            }
            List<SubjectRegistration> list = subjectRegistrationRepository.findByClassSubject(classSubject);
            ExamSchedule examSchedule = ExamSchedule.fromCreateExamScheduleRequest(it, classSubject, list.size());
            examScheduleRepository.save(examSchedule);
        }
        return true;
    }

    @Override
    public ViewExamScheduleResponse getAllExamScheduleByStudent(String studentIdNum) throws Exception {
        Student student = studentRepository.findByIdNum(studentIdNum)
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + studentIdNum));
        List<SubjectRegistration>  subjectRegistrationList = subjectRegistrationRepository.findByStudent(student);

        List<ClassSubject> classSubjects = subjectRegistrationList.stream()
                .map(SubjectRegistration::getClassSubject)
                .toList();

        List<ExamSchedule> examSchedules = examScheduleRepository.findByClassSubjects(classSubjects);

        List<SemesterResponse> semesterResponseList = subjectRegistrationList.stream()
                .map(subjectRegistration -> SemesterResponse.fromSemester(subjectRegistration.getClassSubject().getSemester()))
                .distinct()
                .sorted(Comparator.comparing(SemesterResponse::getStartDate).reversed())
                .toList();

        List<ExamScheduleResponse> examScheduleResponseList = examSchedules.stream()
                .map(examSchedule -> {
                    ClassSubject classSubject = examSchedule.getClassSubject();
                    return ExamScheduleResponse.fromExamSchedule(examSchedule, classSubject);
                })
                .toList();

        return new ViewExamScheduleResponse(semesterResponseList, examScheduleResponseList);
    }
}



















