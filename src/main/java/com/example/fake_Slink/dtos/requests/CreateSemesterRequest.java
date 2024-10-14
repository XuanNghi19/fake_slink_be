package com.example.fake_Slink.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSemesterRequest {
    @NotBlank(message = "Semester name required")
    String semesterName;

    @NotNull(message = "Start date is required")
    Date startDate;

    @NotNull(message = "End date is required")
    Date endDate;
}
