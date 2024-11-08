package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.StudentDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDeviceRepository extends JpaRepository<StudentDevice, Long> {
    List<StudentDevice> findByStudentId(int studentId);
    boolean existsByFcmTokenAndStudent(String fcmToken, int studentId);
}
