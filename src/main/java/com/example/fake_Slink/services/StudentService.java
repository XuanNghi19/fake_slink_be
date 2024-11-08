package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.*;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;

import java.util.List;

public interface StudentService {
    Boolean addStudents(List<CreateStudentRequest> list) throws Exception;
    AuthenticationResponse authentication(AuthenticationRequest authenticationRequests) throws Exception;
    AuthenticationResponse authenticationWithMobilePhone(AuthenticationWithMobilePhoneRequest authenticationRequests) throws Exception;
    StudentResponse getStudentDetail(String token) throws Exception;
    List<StudentResponse> getStudentList(String token) throws Exception;
    StudentResponse updateStudent(String token, UpdateStudentRequest updateStudentRequest) throws Exception;
    Boolean updatePassword(String token, UpdatePasswordRequest updatePasswordRequest) throws Exception;
    Boolean uploadAvatarUrl(UploadAvatarRequest request, String token) throws Exception;
}
