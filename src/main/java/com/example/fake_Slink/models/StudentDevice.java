package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.UpdateStudentDeviceRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_device")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fcm_token")
    private String fcmToken;

    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    static public StudentDevice fromUpdateStudentDeviceRequest(
            UpdateStudentDeviceRequest request,
            Student student
    ) {
        return StudentDevice.builder()
                .fcmToken(request.getFcmToken())
                .student(student)
                .lastUpdated(request.getLastUpdated())
                .build();
    }
}
