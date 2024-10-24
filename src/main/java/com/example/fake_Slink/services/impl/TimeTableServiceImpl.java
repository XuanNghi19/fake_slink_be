package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateTimeTableRequest;
import com.example.fake_Slink.dtos.responses.TimeTableResponse;
import com.example.fake_Slink.models.*;
import com.example.fake_Slink.repositories.ClassSubjectRepository;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.repositories.SubjectRegistrationRepository;
import com.example.fake_Slink.repositories.TimeTableRepository;
import com.example.fake_Slink.services.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {
    private final TimeTableRepository timeTableRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final StudentRepository studentRepository;

    @Override
    public boolean createTimeTable(List<CreateTimeTableRequest> list) throws Exception {
        for (var x : list) {
            ClassSubject classSubject = classSubjectRepository.findById(x.getClassSubjectID()).orElseThrow(
                    () -> new Exception("Khong tim thay classSubject voi id: " + x.getClassSubjectID())
            );

            TimeTable newTimeTable = TimeTable.fromCreateTimeTableRequest(
                    x,
                    classSubject
            );

            timeTableRepository.save(newTimeTable);
        }
        return true;
    }

    @Override
    public List<TimeTableResponse> getTimeTable(String studentIDNum) throws Exception {
        Student student = studentRepository.findByIdNum(studentIDNum).orElseThrow(
                () -> new Exception("Khong tim thay student voi idNum: " + studentIDNum)
        );
        Date today = new Date();
        List<SubjectRegistration> subjectRegistrationList = subjectRegistrationRepository
                .findByStudentAndClassSubjectSemesterEndDate(student, today);


        List<TimeTableResponse> timeTableResponsesList = subjectRegistrationList
                .stream()
                .flatMap(subjectRegistration ->
                        timeTableRepository.findByClassSubject(subjectRegistration.getClassSubject())
                                .stream()
                                .map(timeTable -> TimeTableResponse.fromTimeTable(timeTable, subjectRegistration.getClassSubject()))
                ).toList();
        return timeTableResponsesList;
    }

}
