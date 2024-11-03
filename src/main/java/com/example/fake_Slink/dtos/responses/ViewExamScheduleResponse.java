package com.example.fake_Slink.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ViewExamScheduleResponse {
    List<SemesterResponse> semesterResponseList;

    List<ExamScheduleResponse> examScheduleResponseList;
}
