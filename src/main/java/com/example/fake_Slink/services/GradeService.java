package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.UpdateGradeRequest;
import com.example.fake_Slink.dtos.responses.GradeResponse;
import com.example.fake_Slink.dtos.responses.LearningOutcomesResponse;

import java.util.List;

public interface GradeService {
    Boolean updateGradeFromSubjectClass(List<UpdateGradeRequest> list) throws Exception;
    LearningOutcomesResponse getAllGradeFromStudent(String idNum) throws Exception;
}
