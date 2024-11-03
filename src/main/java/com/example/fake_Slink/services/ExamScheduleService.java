package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateExamScheduleRequest;
import com.example.fake_Slink.dtos.responses.ViewExamScheduleResponse;

import java.util.List;

public interface ExamScheduleService {
    boolean createExamSchedule(List<CreateExamScheduleRequest> requests);
    ViewExamScheduleResponse getAllExamScheduleByStudent(String studentIdNum) throws Exception;
}
