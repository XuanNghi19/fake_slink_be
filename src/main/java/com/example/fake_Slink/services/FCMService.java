package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.GradeNotificationRequest;
import com.example.fake_Slink.dtos.requests.ReviewFormNotificationRequest;
import com.example.fake_Slink.dtos.requests.UpdateStudentDeviceRequest;
import com.example.fake_Slink.models.Student;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface FCMService {
    void sendGradedNotification(GradeNotificationRequest request);
    void sendReviewFormNotification(ReviewFormNotificationRequest request);
    void updateStudentDevice(UpdateStudentDeviceRequest request, Student student) throws Exception;
}
