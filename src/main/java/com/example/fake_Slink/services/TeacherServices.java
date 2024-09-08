package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.AuthenticationRequest;
import com.example.fake_Slink.dtos.requests.CreateTeacherRequest;
import com.example.fake_Slink.dtos.requests.IntrospectRequest;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;
import com.example.fake_Slink.dtos.responses.TeacherResponse;

import java.util.List;

public interface TeacherServices {
    Boolean addTeachers(List<CreateTeacherRequest> list) throws Exception;
    Boolean addAdmin(CreateTeacherRequest admin) throws Exception;
    AuthenticationResponse authentication(AuthenticationRequest authenticationRequests) throws Exception;
}