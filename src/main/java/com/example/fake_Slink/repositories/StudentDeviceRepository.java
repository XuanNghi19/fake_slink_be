package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.StudentDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDeviceRepository extends JpaRepository<StudentDevice, Long> {
    List<StudentDevice> findByStudent(Student student);
    boolean existsByFcmTokenAndStudent(String fcmToken, Student student);
}
