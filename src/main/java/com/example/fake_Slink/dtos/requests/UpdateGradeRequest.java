package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.models.ClassSubject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateGradeRequest {
    String studentIdNum;

    int classSubjectID;

    float diemCC;

    float diemBT;

    float diemTH;

    float diemKT;

    float diemCK;

    String status;
}
