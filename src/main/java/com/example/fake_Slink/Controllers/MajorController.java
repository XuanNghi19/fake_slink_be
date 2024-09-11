package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.CreateMajorRequest;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.services.MajorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/majors")
public class MajorController {
    private final MajorService majorService;

    @PostMapping("/add_majors")
    public ApiResponse<?> addMajors(
            @RequestBody @Valid List<CreateMajorRequest> requests,
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
            Boolean status = majorService.addMajors(requests);
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
