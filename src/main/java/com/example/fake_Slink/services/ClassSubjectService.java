package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;

import java.util.List;

public interface ClassSubjectService {
    boolean createClassSubjects(List<CreateClassSubjectRequest> list) throws Exception;
    ClassSubjectResponse getClassSubject(int classSubjectID) throws Exception;
}
