package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "class_subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classSubjectID;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "location")
    private String location;

    @Column(name = "class_group")
    private int group;

    @Column(name = "class_name")
    private String className;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "percent_diem_CC")
    private float percentDiemCC;

    @Column(name = "percent_diem_BT")
    private float percentDiemBT;

    @Column(name = "percent_diem_TH")
    private float percentDiemTH;

    @Column(name = "percent_diem_KT")
    private float percentDiemKT;

    @Column(name = "percent_diem_CK")
    private float percentDiemCK;

    public static ClassSubject fromCreateClassSubjectRequest(
            CreateClassSubjectRequest request,
            Semester semester,
            Teacher teacher,
            Subject subject
    ) {
        return ClassSubject.builder()
                .semester(semester)
                .teacher(teacher)
                .subject(subject)
                .location(request.getLocation())
                .group(request.getGroup())
                .className(request.getClassName())
                .registrationDate(request.getRegistrationDate())
                .percentDiemCC(request.getPercentDiemCC())
                .percentDiemBT(request.getPercentDiemBT())
                .percentDiemTH(request.getPercentDiemTH())
                .percentDiemKT(request.getPercentDiemKT())
                .percentDiemCK(request.getPercentDiemCK())
                .build();
    }
}
