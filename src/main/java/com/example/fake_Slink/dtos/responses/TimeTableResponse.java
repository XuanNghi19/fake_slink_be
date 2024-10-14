package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.TimeTable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeTableResponse {
    int id;
    
    ClassSubjectResponse classSubjectResponse;

    int dayOfWeek;

    String startTime;

    String endTime;

    public static TimeTableResponse fromTimeTable(
            TimeTable timeTable,
            ClassSubject classSubject
    ) {
        return TimeTableResponse.builder()
                .id(timeTable.getId())
                .classSubjectResponse(ClassSubjectResponse.fromClassSubject(classSubject))
                .dayOfWeek(timeTable.getDayOfWeek())
                .startTime(timeTable.getStartTime())
                .endTime(timeTable.getEndTime())
                .build();
    }
}
