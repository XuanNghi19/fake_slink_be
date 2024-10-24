package com.example.fake_Slink.Controllers;

import com.example.fake_Slink.dtos.requests.UpdateGradeRequest;
import com.example.fake_Slink.dtos.responses.ApiResponse;
import com.example.fake_Slink.dtos.responses.LearningOutcomesResponse;
import com.example.fake_Slink.dtos.responses.SemesterResponse;
import com.example.fake_Slink.services.GradeService;
import com.example.fake_Slink.services.SemesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/grade")
public class GradeController {
    private final GradeService gradeService;

    @PutMapping
    public ApiResponse<Boolean> updateGradeFromSubjectClass(
            @RequestBody @Valid List<UpdateGradeRequest> list,
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
            Boolean status = gradeService.updateGradeFromSubjectClass(list);
            return ApiResponse.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .result(status)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/{id_num}")
    public ApiResponse<LearningOutcomesResponse> getDataForLearningOutcomes(
            @PathVariable("id_num") String idNum
    ) {
        try {
            LearningOutcomesResponse learningOutcomesResponse = gradeService.getAllGradeFromStudent(idNum);
            return ApiResponse.<LearningOutcomesResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(learningOutcomesResponse)
                    .message(HttpStatus.OK.toString())
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
