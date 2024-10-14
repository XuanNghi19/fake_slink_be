package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.Semester;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SemesterResponse {
    int semesterID;
    String semesterName;
    Date startDate;
    Date endDate;

    public static SemesterResponse fromSemester(Semester semester) {
        return SemesterResponse.builder()
                .semesterID(semester.getSemesterId())
                .semesterName(semester.getSemesterName())
                .startDate(semester.getStartDate())
                .endDate(semester.getEndDate())
                .build();
    }
}
