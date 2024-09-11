package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateSubjectRequest;

import java.util.List;

public interface SubjectService {
    boolean addSubject(List<CreateSubjectRequest> list) throws Exception;
}
