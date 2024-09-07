package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.*;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;

import java.util.List;

public interface StudentServices {
    Boolean addStudents(List<CreateStudentRequest> list) throws Exception;
    AuthenticationResponse authentication(AuthenticationRequest authenticationRequests) throws Exception;
    StudentResponse getStudentDetail(IntrospectRequest introspectRequest) throws Exception;
    StudentResponse updateStudent(UpdateStudentRequest updateStudentRequest) throws Exception;
    Boolean updatePassword(UpdatePasswordRequest updatePasswordRequest) throws Exception;

}
