package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.GradeNotificationRequest;
import com.example.fake_Slink.dtos.requests.ReviewFormNotificationRequest;
import com.example.fake_Slink.services.FCMService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

import jakarta.annotation.PostConstruct;

@Slf4j
@Service
public class FCMServiceImpl implements FCMService {

    @PostConstruct
    public void initialize() {
        String firebaseConfigPath = System.getenv("FIREBASE_CONFIG_JSON");
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .build();

            FirebaseApp firebaseApp;
            if(FirebaseApp.getApps().isEmpty()) {
                firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            log.error("Create firebase error: " + e.getMessage());
        }
    }

    @Override
    public void sendGradedNotification(GradeNotificationRequest request) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setToken(request.getFcmToken())
                .putData("classify", request.getClassify())
                .putData("gradeResponse", new Gson().toJson(request.getGradeResponse()))
                .putData("title", request.getTitle())
                .putData("message", request.getMessage())
                .putData("createAt", new Gson().toJson(request.getCreateAt()))
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        log.info("Successfully send notification: " + response);
    }

    @Override
    public void sendReviewFormNotification(ReviewFormNotificationRequest request) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setToken(request.getFcmToken())
                .putData("classify", request.getClassify())
                .putData("gradeResponse", new Gson().toJson(request.getReviewFormResponse()))
                .putData("title", request.getTitle())
                .putData("message", request.getMessage())
                .putData("createAt", new Gson().toJson(request.getCreateAt()))
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        log.info("Successfully send notification: " + response);
    }
}
