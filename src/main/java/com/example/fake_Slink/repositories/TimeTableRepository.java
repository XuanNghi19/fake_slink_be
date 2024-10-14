package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    List<TimeTable> findByClassSubject(ClassSubject classSubject);
}
