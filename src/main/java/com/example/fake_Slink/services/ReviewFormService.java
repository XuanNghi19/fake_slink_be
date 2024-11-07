package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateReviewFormRequest;
import com.example.fake_Slink.dtos.requests.UpdateReviewFormStatusRequest;
import com.example.fake_Slink.dtos.responses.ReviewFormResponse;
import com.example.fake_Slink.enums.ReviewFormStatus;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.List;

public interface ReviewFormService {
    boolean createReviewForm(CreateReviewFormRequest request);
    boolean updateStatus(UpdateReviewFormStatusRequest request) throws FirebaseMessagingException;
    List<ReviewFormResponse> getAllReviewFormByStudent(String studentIdNum) throws Exception;
}
