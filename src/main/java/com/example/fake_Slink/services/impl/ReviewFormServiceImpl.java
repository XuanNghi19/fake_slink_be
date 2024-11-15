package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateReviewFormRequest;
import com.example.fake_Slink.dtos.requests.GradeNotificationRequest;
import com.example.fake_Slink.dtos.requests.ReviewFormNotificationRequest;
import com.example.fake_Slink.dtos.requests.UpdateReviewFormStatusRequest;
import com.example.fake_Slink.dtos.responses.GradeResponse;
import com.example.fake_Slink.dtos.responses.ReviewFormResponse;
import com.example.fake_Slink.dtos.responses.SubjectResponse;
import com.example.fake_Slink.enums.ReviewFormStatus;
import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.ReviewForm;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.StudentDevice;
import com.example.fake_Slink.repositories.ClassSubjectRepository;
import com.example.fake_Slink.repositories.ReviewFormRepository;
import com.example.fake_Slink.repositories.StudentDeviceRepository;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.services.FCMService;
import com.example.fake_Slink.services.ReviewFormService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewFormServiceImpl implements ReviewFormService {

    private final StudentRepository studentRepository;
    private final ReviewFormRepository reviewFormRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final StudentDeviceRepository studentDeviceRepository;
    private final FCMService fcmService;

    @Override
    public boolean createReviewForm(CreateReviewFormRequest request) {
        Student student = new Student();
        try{
            student = studentRepository.findByIdNum(request.getStudentIdNum())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay student voi idNum: " + request.getStudentIdNum()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

            return false;
        }

        ClassSubject classSubject = new ClassSubject();
        try{
            classSubject = classSubjectRepository.findById(request.getClassSubjectID())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay classSubject voi id: " + request.getClassSubjectID()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

            return false;
        }

        ReviewForm reviewForm = ReviewForm.fromCreateReviewFormRequest(
                request,
                student,
                classSubject
        );

        reviewFormRepository.save(reviewForm);

        return true;
    }

    @Override
    public boolean updateStatus(UpdateReviewFormStatusRequest request) throws FirebaseMessagingException {
        ReviewForm reviewForm = new ReviewForm();
        try {
            reviewForm = reviewFormRepository.findById(request.getReviewFormID())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay reviewForm voi ID: " + request.getReviewFormID()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }

        reviewForm.setStatus(request.getStatus());
        reviewFormRepository.save(reviewForm);

        ReviewFormNotificationRequest notificationRequest = ReviewFormNotificationRequest.fromReviewFormResponse(
                ReviewFormResponse.fromReviewForm(reviewForm),
                SubjectResponse.fromSubject(reviewForm.getClassSubject().getSubject())
        );
        List<StudentDevice> deviceList = studentDeviceRepository.findByStudent(reviewForm.getStudent());

        for(var device : deviceList) {
            notificationRequest.setFcmToken(device.getFcmToken());
            fcmService.sendReviewFormNotification(notificationRequest);
        }
        return true;
    }

    @Override
    public List<ReviewFormResponse> getAllReviewFormByStudent(String studentIdNum) throws Exception {
        List<ReviewFormResponse> responses = reviewFormRepository.findByStudentIdNum(studentIdNum)
                .stream()
                .map(ReviewFormResponse::fromReviewForm)
                .toList();
        return responses;
    }
}
