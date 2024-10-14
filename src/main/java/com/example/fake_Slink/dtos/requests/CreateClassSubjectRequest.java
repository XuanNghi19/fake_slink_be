package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.models.Semester;
import com.example.fake_Slink.models.Subject;
import com.example.fake_Slink.models.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateClassSubjectRequest {

    @NotNull(message = "semesterID is required")
    int semesterID;

    @NotBlank(message = "teacherID is required")
    String teacherIDNum;

    @NotBlank(message = "subjectID is required")
    String subjectIDNum;

    @NotBlank(message = "location is required")
    String location;

    @NotNull(message = "group is required")
    int group;

    @NotBlank(message = "group is required")
    String className;

    @NotNull(message = "registrationDate is required")
    Date registrationDate;
    
    float percentDiemCC;
    
    float percentDiemBT;

    float percentDiemTH;

    float percentDiemKT;

    float percentDiemCK;
}
