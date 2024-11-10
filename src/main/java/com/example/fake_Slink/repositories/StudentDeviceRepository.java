package com.example.fake_Slink.repositories;

import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.StudentDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentDeviceRepository extends JpaRepository<StudentDevice, Long> {
    List<StudentDevice> findByStudent(Student student);
    @Query("SELECT COUNT(sd) > 0 FROM StudentDevice sd WHERE sd.fcmToken = :fcmToken AND sd.student = :student")
    boolean existsByFcmTokenAndStudent(@Param("fcmToken") String fcmToken, @Param("student") Student student);

}
