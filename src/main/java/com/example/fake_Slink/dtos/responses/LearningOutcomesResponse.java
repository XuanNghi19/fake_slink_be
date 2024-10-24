package com.example.fake_Slink.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LearningOutcomesResponse {
    List<SemesterResponse> semesterResponseList;
    List<GradeResponse> gradeResponseList;
}
