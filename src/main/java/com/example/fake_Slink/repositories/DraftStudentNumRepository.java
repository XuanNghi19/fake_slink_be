package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.DraftStudentNum;
import com.example.fake_Slink.models.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftStudentNumRepository extends JpaRepository<DraftStudentNum, Integer> {
    DraftStudentNum findByCourseAndMajor(String course, Major major);
}
