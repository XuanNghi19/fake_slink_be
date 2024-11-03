package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.CreateExamScheduleRequest;
import com.example.fake_Slink.dtos.requests.CreateReviewFormRequest;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.ReviewFormResponse;
import com.example.fake_Slink.dtos.responses.ViewExamScheduleResponse;
import com.example.fake_Slink.services.ExamScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/examSchedule")
public class ExamScheduleController {
    private final ExamScheduleService examScheduleService;

    @PostMapping
    public ApiResponse<Boolean> createReviewForm(
            @RequestBody @Valid List<CreateExamScheduleRequest> requests,
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
            Boolean status = examScheduleService.createExamSchedule(requests);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/{student_id_num}")
    public ApiResponse<ViewExamScheduleResponse> getAllReviewFormByStudent(
            @PathVariable("student_id_num") String studentIdNum
    ) {
        try {
            ViewExamScheduleResponse response = examScheduleService.getAllExamScheduleByStudent(studentIdNum);
            return ApiResponse.<ViewExamScheduleResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
