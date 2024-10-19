package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.*;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;
import com.example.fake_Slink.services.StudentService;
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

    private final StudentService studentServices;

    @PostMapping("/add_students")
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
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            Boolean status = studentServices.addStudents(list);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
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
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(errorMessages.toString())
                    .result(false)
                    .build();
        }

        try {
            AuthenticationResponse response = studentServices.authentication(authenticationRequest);
            return ApiResponse.<AuthenticationResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/student_detail")
    public ApiResponse<?> getStudentDetail(
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            String token = authorizationHeader.substring(7);
            StudentResponse response = studentServices.getStudentDetail(token);
            return ApiResponse.<StudentResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/get_student_list")
    public ApiResponse<?> getStudentList(
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            String token = authorizationHeader.substring(7);
            List<StudentResponse> list = studentServices.getStudentList(token);
            return ApiResponse.<List<StudentResponse>>builder()
                    .code(HttpStatus.OK.value())
                    .result(list)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PutMapping("/update_student_detail")
    public ApiResponse<?> updateStudentDetail(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid UpdateStudentRequest request,
            BindingResult result
    ) {
        if(result.hasErrors()) {
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
            String token = authorizationHeader.substring(7);
            StudentResponse response = studentServices.updateStudent(token, request);
            return ApiResponse.<StudentResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PatchMapping("/update_password")
    public ApiResponse<?> updatePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid UpdatePasswordRequest request,
            BindingResult result
    ) {
        if(result.hasErrors()) {
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
            String token = authorizationHeader.substring(7);
            Boolean status = studentServices.updatePassword(token, request);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PatchMapping("/upload_avatar_url")
    public ApiResponse<?> uploadAvatarUrl(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid UploadAvatarRequest request,
            BindingResult result
    ) {
        if(result.hasErrors()) {
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
            String token = authorizationHeader.substring(7);
            Boolean status = studentServices.uploadAvatarUrl(request, token);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}