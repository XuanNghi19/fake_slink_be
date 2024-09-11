package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Boolean existsByIdNum(String idNum);
}
