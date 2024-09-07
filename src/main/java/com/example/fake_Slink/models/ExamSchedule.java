package com.example.fake_Slink.models;

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

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "minute")
    private String minute;
}
