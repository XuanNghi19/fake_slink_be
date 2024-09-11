package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateDepartmentRequest;
import com.example.fake_Slink.models.Department;
import com.example.fake_Slink.repositories.DepartmentRepository;
import com.example.fake_Slink.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public boolean addDepartments(List<CreateDepartmentRequest> list) throws Exception {
        for (var x : list) {
            if(departmentRepository.existsById(x.getId())) {
                throw new RuntimeException("id da ton tai");
            }
            Department newDepartment = Department.fromCreateDepartmentRequest(x);
            departmentRepository.save(newDepartment);
        }
        return true;
    }
}
