package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import com.example.fake_Slink.dtos.responses.*;
import com.example.fake_Slink.services.ClassSubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/classSubject")
public class ClassSubjectController {
    private final ClassSubjectService classSubjectService;

    @PostMapping
    public ApiResponse<Boolean> createClassSubject(
            @RequestBody @Valid List<CreateClassSubjectRequest> requests,
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
            Boolean status = classSubjectService.createClassSubjects(requests);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/{classSubjectID}")
    public ApiResponse<?> getClassSubject(
            @PathVariable("classSubjectID") int classSubjectID
    ) {
        try {
            ClassSubjectResponse response = classSubjectService.getClassSubject(classSubjectID);
            return ApiResponse.<ClassSubjectResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/credit_class/{student_id_num}")
    public ApiResponse<?> getCreditClass(
            @PathVariable("student_id_num") String studentIdNum
    ) {
        try {
            CreditClassResponse response = classSubjectService.getCreditClass(studentIdNum);
            return ApiResponse.<CreditClassResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/get_students_in_credit_class/{classSubjectID}")
    public ApiResponse<?> getCreditClass(
            @PathVariable("classSubjectID") int classSubjectID
    ) {
        try {
            List<StudentResponse> response = classSubjectService.getStudentInCreditClass(classSubjectID);
            return ApiResponse.<List<StudentResponse>>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
