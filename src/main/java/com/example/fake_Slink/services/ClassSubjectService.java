package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;
import com.example.fake_Slink.dtos.responses.CreditClassResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;
import com.example.fake_Slink.models.SubjectRegistration;

import java.util.List;

public interface ClassSubjectService {
    boolean createClassSubjects(List<CreateClassSubjectRequest> list) throws Exception;
    ClassSubjectResponse getClassSubject(int classSubjectID) throws Exception;
    CreditClassResponse getCreditClass(String idNum) throws Exception;
    List<StudentResponse> getStudentInCreditClass(int classSubjectId) throws Exception;
}
