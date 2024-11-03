package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateReviewFormRequest;
import com.example.fake_Slink.dtos.requests.UpdateReviewFormStatusRequest;
import com.example.fake_Slink.dtos.responses.ReviewFormResponse;
import com.example.fake_Slink.enums.ReviewFormStatus;

import java.util.List;

public interface ReviewFormService {
    boolean createReviewForm(CreateReviewFormRequest request);
    boolean updateStatus(UpdateReviewFormStatusRequest request);
    List<ReviewFormResponse> getAllReviewFormByStudent(String studentIdNum) throws Exception;
}
