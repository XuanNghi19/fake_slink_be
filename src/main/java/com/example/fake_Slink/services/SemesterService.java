package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateSemesterRequest;
import com.example.fake_Slink.dtos.responses.SemesterResponse;

import java.util.List;

public interface SemesterService {
    boolean createSemester(CreateSemesterRequest request) throws Exception;
    SemesterResponse getSemester(int idSemester) throws Exception;
    List<SemesterResponse> getAllSemester() throws Exception;
}
