package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateSubjectRegistrationRequest;

import java.util.List;

public interface SubjectRegistrationService {
    boolean createSubjectRegistration(List<CreateSubjectRegistrationRequest> list) throws Exception;
}
