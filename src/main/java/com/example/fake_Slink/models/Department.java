package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateDepartmentRequest;
import com.example.fake_Slink.dtos.requests.CreateMajorRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    private String id;

    @Column(name = "department_name")
    private String departmentName;

    public static Department fromCreateDepartmentRequest(CreateDepartmentRequest request) {
        return Department.builder()
                .id(request.getId())
                .departmentName(request.getDepartmentName())
                .build();
    }
}
