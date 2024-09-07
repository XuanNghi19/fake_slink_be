package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepositories extends JpaRepository<Department, String> {
}
