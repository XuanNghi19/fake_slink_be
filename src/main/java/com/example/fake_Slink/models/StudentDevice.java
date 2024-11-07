package com.example.fake_Slink.models;

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
    private int studentId;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
