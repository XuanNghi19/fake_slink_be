package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.Grade;
import com.example.fake_Slink.models.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GradeResponse {
    ClassSubjectResponse classSubjectResponse;

    float diemCC;

    float diemBT;

    float diemTH;

    float diemKT;

    float diemCK;

    float diemTK;

    String status;

    Date appealsDateline;

    static public GradeResponse fromGrade(Grade grade) {
        return GradeResponse.builder()
                .classSubjectResponse(ClassSubjectResponse.fromClassSubject(grade.getClassSubject()))
                .diemCC(grade.getDiemCC())
                .diemBT(grade.getDiemBT())
                .diemTH(grade.getDiemTH())
                .diemKT(grade.getDiemKT())
                .diemCK(grade.getDiemCK())
                .diemTK(grade.getDiemTK())
                .status(grade.getStatus())
                .appealsDateline(grade.getAppealsDateline())
                .build();
    }
}
