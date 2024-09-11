package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateDepartmentRequest;

import java.util.List;

public interface DepartmentService {
    boolean addDepartments(List<CreateDepartmentRequest> list) throws Exception;
}
