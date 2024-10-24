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
        List<SubjectRegistration> subjectRegistrationList = subjectRegistrationRepository.findByStudent(student);
        Date today = new Date();
        for(var x : subjectRegistrationList) {
            if(x.getClassSubject().getSemester().getEndDate().before(today)){
                subjectRegistrationList.remove(x);
            }
        }


        List<TimeTableResponse> timeTableResponsesList = new ArrayList<>();
        for (var x : subjectRegistrationList) {
            List<TimeTable> timeTableListForEachClassSubject = timeTableRepository.findByClassSubject(x.getClassSubject());
            timeTableResponsesList.addAll(timeTableListForEachClassSubject
                    .stream()
                    .map(timeTable -> TimeTableResponse.fromTimeTable(timeTable, x.getClassSubject()))
                    .toList()
            );
        }
        return timeTableResponsesList;
    }

}
