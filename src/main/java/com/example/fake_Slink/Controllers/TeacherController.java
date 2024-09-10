package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.AuthenticationRequest;
import com.example.fake_Slink.dtos.requests.CreateTeacherRequest;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/teachers")
public class TeacherController {

    private final TeacherService teacherServices;

    @PostMapping("/add_teacher")
    public ApiResponse<?> addTeacher(
            @RequestBody @Valid List<CreateTeacherRequest> requests,
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
            Boolean status = teacherServices.addTeachers(requests);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/add_admin")
    public ApiResponse<?> addTeacher(
            @RequestBody @Valid CreateTeacherRequest request,
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
            Boolean status = teacherServices.addAdmin(request);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/teacher_authentication")
    public ApiResponse<?> addTeacher(
            @RequestBody @Valid AuthenticationRequest request,
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
            AuthenticationResponse response = teacherServices.authentication(request);
            return ApiResponse.<AuthenticationResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
