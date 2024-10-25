package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.Semester;
import com.example.fake_Slink.models.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassSubjectResponse {
    int classSubjectID;

    SemesterResponse semesterResponse;

    TeacherResponse teacherResponse;

    SubjectResponse subjectResponse;

    String location;

    int group;

    String className;

    Date registrationDate;

    float percentDiemCC;

    float percentDiemBT;

    float percentDiemTH;

    float percentDiemKT;

    float percentDiemCK;

    public static ClassSubjectResponse fromClassSubject(ClassSubject classSubject) {
        return ClassSubjectResponse.builder()
                .classSubjectID(classSubject.getClassSubjectID())
                .semesterResponse(SemesterResponse.fromSemester(classSubject.getSemester()))
                .teacherResponse(TeacherResponse.fromTeacher(classSubject.getTeacher()))
                .subjectResponse(SubjectResponse.fromSubject(classSubject.getSubject()))
                .location(classSubject.getLocation())
                .group(classSubject.getGroup())
                .className(classSubject.getClassName())
                .registrationDate(classSubject.getRegistrationDate())
                .percentDiemCC(classSubject.getPercentDiemCC())
                .percentDiemBT(classSubject.getPercentDiemBT())
                .percentDiemTH(classSubject.getPercentDiemTH())
                .percentDiemKT(classSubject.getPercentDiemKT())
                .percentDiemCK(classSubject.getPercentDiemCK())
                .build();
    }
}
