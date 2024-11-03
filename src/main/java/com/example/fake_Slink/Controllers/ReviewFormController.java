package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.CreateReviewFormRequest;
import com.example.fake_Slink.dtos.requests.UpdateReviewFormStatusRequest;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.ReviewFormResponse;
import com.example.fake_Slink.enums.ReviewFormStatus;
import com.example.fake_Slink.services.ReviewFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/reviewForm")
public class ReviewFormController {
    private final ReviewFormService reviewFormService;

    @PostMapping
    public ApiResponse<Boolean> createReviewForm(
            @RequestBody @Valid CreateReviewFormRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            Boolean status = reviewFormService.createReviewForm(request);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PatchMapping
    public ApiResponse<Boolean> updateStatus(
            @RequestBody @Valid UpdateReviewFormStatusRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            Boolean status = reviewFormService.updateStatus(request);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/by_student/{student_id_num}")
    public ApiResponse<List<ReviewFormResponse>> getAllReviewFormByStudent(
            @PathVariable("student_id_num") String studentIdNum
    ) {
        try {
            List<ReviewFormResponse> responses = reviewFormService.getAllReviewFormByStudent(studentIdNum);
            return ApiResponse.<List<ReviewFormResponse>>builder()
                    .code(HttpStatus.OK.value())
                    .result(responses)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
