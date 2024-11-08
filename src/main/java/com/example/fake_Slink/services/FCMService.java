package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.GradeNotificationRequest;
import com.example.fake_Slink.dtos.requests.ReviewFormNotificationRequest;
import com.example.fake_Slink.dtos.requests.UpdateStudentDeviceRequest;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface FCMService {
    void sendGradedNotification(GradeNotificationRequest request) throws FirebaseMessagingException;
    void sendReviewFormNotification(ReviewFormNotificationRequest request) throws FirebaseMessagingException;
    void updateStudentDevice(UpdateStudentDeviceRequest request) throws Exception;
}
