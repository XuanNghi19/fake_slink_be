package com.example.fake_Slink.models;

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
    @JoinColumn(name = "semesterId")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "teacherID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subjectID")
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

}
