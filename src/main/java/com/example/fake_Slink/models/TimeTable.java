package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateTimeTableRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "classSubjectID")
    private ClassSubject classSubject;

    private int dayOfWeek;

    private String startTime;

    private String endTime;

    public static TimeTable fromCreateTimeTableRequest(
            CreateTimeTableRequest request,
            ClassSubject classSubject
    ) {
        return TimeTable.builder()
                .classSubject(classSubject)
                .dayOfWeek(request.getDayOfWeek())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();
    }
}
