package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.configs.LocalDateTimeSerializer;
import com.example.fake_Slink.dtos.requests.GradeNotificationRequest;
import com.example.fake_Slink.dtos.requests.ReviewFormNotificationRequest;
import com.example.fake_Slink.dtos.requests.UpdateStudentDeviceRequest;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.StudentDevice;
import com.example.fake_Slink.repositories.StudentDeviceRepository;
import com.example.fake_Slink.services.FCMService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class FCMServiceImpl implements FCMService {

    private final StudentDeviceRepository studentDeviceRepository;

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    private FirebaseApp firebaseApp;

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            log.error("Create firebase error: " + e.getMessage());
        }
    }

    @Override
    public void sendGradedNotification(GradeNotificationRequest request) {
        Message message = Message.builder()
                .setToken(request.getFcmToken())
                .putData("classify", request.getClassify())
                .putData("gradeResponse", new Gson().toJson(request.getGradeResponse()))
                .putData("title", request.getTitle())
                .putData("message", request.getMessage())
                .putData("createAt", new Gson().toJson(request.getCreateAt()))
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully send notification: " + response);
        } catch (FirebaseMessagingException e) {
            log.error("Error sending notification, " + e.getMessage());

            if (e.getMessage().contains("InvalidRegistration") || e.getMessage().contains("NotRegistered")) {
                log.error("FCM token is invalid or the device is no longer active.");
            } else {
                log.error("Unknown FirebaseMessagingException: " + e.getMessage());
            }
        }
    }

    @Override
    public void sendReviewFormNotification(ReviewFormNotificationRequest request) {
        Message message = Message.builder()
                .setToken(request.getFcmToken())
                .putData("classify", request.getClassify())
                .putData("reviewFormResponse", new Gson().toJson(request.getReviewFormResponse()))
                .putData("title", request.getTitle())
                .putData("message", request.getMessage())
                .putData("createAt", new Gson().toJson(request.getCreateAt()))
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully send notification: " + response);
        } catch (FirebaseMessagingException e) {
            log.error("Error sending notification, " + e.getMessage());

            if (e.getMessage().contains("InvalidRegistration") || e.getMessage().contains("NotRegistered")) {
                log.error("FCM token is invalid or the device is no longer active.");
            } else {
                log.error("Unknown FirebaseMessagingException: " + e.getMessage());
            }
        }
    }

    @Override
    public void updateStudentDevice(UpdateStudentDeviceRequest request, Student student) throws Exception {
        if(studentDeviceRepository.existsByFcmTokenAndStudent(request.getFcmToken(), student)) {
            return;
        }

        studentDeviceRepository.save(StudentDevice.fromUpdateStudentDeviceRequest(
                request,
                student
        ));
    }


}
