package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateExamScheduleRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "exam_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examScheduleID;

    @ManyToOne
    @JoinColumn(name = "classSubjectID")
    private ClassSubject classSubject;

    @Column(name = "date_of_event")
    private Date dateOfEvent;

    @Column(name = "location")
    private String location;

    @Column(name = "number_of_student")
    private int numberOfStudent;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "minute")
    private String minute;

    @Column(name = "format")
    private String format;

    static public ExamSchedule fromCreateExamScheduleRequest(
            CreateExamScheduleRequest request,
            ClassSubject classSubject,
            int numberOfStudent
    ) {
        return ExamSchedule.builder()
                .classSubject(classSubject)
                .dateOfEvent(request.getDateOfEvent())
                .location(request.getLocation())
                .numberOfStudent(numberOfStudent)
                .startTime(request.getStartTime())
                .minute(request.getMinute())
                .format(request.getFormat())
                .build();
    }
}
