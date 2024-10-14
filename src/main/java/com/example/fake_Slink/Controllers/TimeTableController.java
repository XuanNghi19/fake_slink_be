package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.CreateTimeTableRequest;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;
import com.example.fake_Slink.dtos.responses.TimeTableResponse;
import com.example.fake_Slink.services.TimeTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/timeTables")
public class TimeTableController {
    private final TimeTableService timeTableService;

    @PostMapping
    public ApiResponse<Boolean> createTimeTable(
            @RequestBody @Valid List<CreateTimeTableRequest> requests,
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
            Boolean status = timeTableService.createTimeTable(requests);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/{studentIDNum}")
    public  ApiResponse<?> getTimeTable(
            @PathVariable("studentIDNum") String studentIDNum
    ) {
        try {
            List<TimeTableResponse> response = timeTableService.getTimeTable(studentIDNum);
            return ApiResponse.<List<TimeTableResponse>>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
