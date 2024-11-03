package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.ExamSchedule;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExamScheduleResponse {
    ClassSubjectResponse classSubjectResponse;

    Date dateOfEvent;

    String location;

    int numberOfStudent;

    String startTime;

    String minute;

    String format;

    static public ExamScheduleResponse fromExamSchedule(
            ExamSchedule examSchedule,
            ClassSubject classSubject
    ) {
        return ExamScheduleResponse.builder()
                .classSubjectResponse(ClassSubjectResponse.fromClassSubject(classSubject))
                .dateOfEvent(examSchedule.getDateOfEvent())
                .location(examSchedule.getLocation())
                .numberOfStudent(examSchedule.getNumberOfStudent())
                .startTime(examSchedule.getStartTime())
                .minute(examSchedule.getMinute())
                .format(examSchedule.getFormat())
                .build();
    }
}
