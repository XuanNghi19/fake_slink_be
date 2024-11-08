package com.example.fake_Slink.dtos.requests;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateStudentDeviceRequest {
    
    String fcmToken;
    
    String studentIdNum;
    
    LocalDateTime lastUpdated;
}
