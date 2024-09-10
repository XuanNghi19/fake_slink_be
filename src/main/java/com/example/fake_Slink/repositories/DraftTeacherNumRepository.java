package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Department;
import com.example.fake_Slink.models.DraftTeacherNum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftTeacherNumRepository extends JpaRepository<DraftTeacherNum, Integer> {
    DraftTeacherNum findByDepartment(Department department);
}
