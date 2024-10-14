package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateTimeTableRequest;
import com.example.fake_Slink.dtos.responses.TimeTableResponse;

import java.util.List;

public interface TimeTableService {
    boolean createTimeTable(List<CreateTimeTableRequest> list) throws Exception;
    List<TimeTableResponse> getTimeTable(String studentIDNum) throws Exception;
}
