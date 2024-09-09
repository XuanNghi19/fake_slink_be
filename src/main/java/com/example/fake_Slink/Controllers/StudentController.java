package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.*;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;
import com.example.fake_Slink.services.StudentServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/students")
public class StudentController {

    private final StudentServices studentServices;

    @PutMapping("/addStudents")
    public ApiResponse<Boolean> addStudents(
            @RequestBody @Valid List<CreateStudentRequest> list,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ApiResponse.<Boolean>builder()
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            Boolean status = studentServices.addStudents(list);
            return ApiResponse.<Boolean>builder()
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/student_authentication")
    public ApiResponse<?> studentAuthentication(
            @RequestBody @Valid AuthenticationRequest authenticationRequest,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<Boolean>builder()
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            AuthenticationResponse response = studentServices.authentication(authenticationRequest);
            return ApiResponse.<AuthenticationResponse>builder()
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/student_detail")
    public ApiResponse<?> getStudentDetail(
            @RequestBody @Valid IntrospectRequest request,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<Boolean>builder()
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            StudentResponse response = studentServices.getStudentDetail(request);
            return ApiResponse.<StudentResponse>builder()
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/get_student_list")
    public ApiResponse<?> getStudentList(
        @RequestBody @Valid IntrospectRequest request,
        BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<Boolean>builder()
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            List<StudentResponse> list = studentServices.getStudentList(request);
            return ApiResponse.<List<StudentResponse>>builder()
                    .result(list)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/update_student_detail")
    public ApiResponse<?> updateStudentDetail(
            @RequestBody @Valid UpdateStudentRequest request,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<Boolean>builder()
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            StudentResponse response = studentServices.updateStudent(request);
            return ApiResponse.<StudentResponse>builder()
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/update_password")
    public ApiResponse<?> updatePassword(
            @RequestBody @Valid UpdatePasswordRequest request,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<Boolean>builder()
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            Boolean status = studentServices.updatePassword(request);
            return ApiResponse.<Boolean>builder()
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}