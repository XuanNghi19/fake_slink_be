package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.ReviewForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewFormRepository extends JpaRepository<ReviewForm, Integer> {
    List<ReviewForm> findByStudentIdNum(String studentIdNum);
}
