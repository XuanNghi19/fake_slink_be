package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.models.ClassSubject;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTimeTableRequest {
    int classSubjectID;

    int dayOfWeek;

    String startTime;

    String endTime;
}
