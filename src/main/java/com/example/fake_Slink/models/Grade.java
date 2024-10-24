package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.UpdateGradeRequest;
import com.example.fake_Slink.models.embeddable.GradeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {
    @EmbeddedId
    private GradeId gradeId;

    @ManyToOne
    @MapsId("studentID")
    @JoinColumn(name = "studentID")
    private Student student;

    @ManyToOne
    @MapsId("classSubjectID")
    @JoinColumn(name = "classSubjectID")
    private ClassSubject classSubject;

    @Column(name = "diemCC")
    private float diemCC;

    @Column(name = "diemBT")
    private float diemBT;

    @Column(name = "diemTH")
    private float diemTH;

    @Column(name = "diemKT")
    private float diemKT;

    @Column(name = "diemCK")
    private float diemCK;

    @Column(name = "diemTK")
    private float diemTK;

    @Column(name = "status")
    private String status;

    static public Grade fromSubjectRegistration(Student student, ClassSubject classSubject) {
        return Grade.builder()
                .gradeId(GradeId.fromCreateClassSubjectRequest(student.getId(), classSubject.getClassSubjectID()))
                .student(student)
                .classSubject(classSubject)
                .status("Chưa hoàn thành môn học")
                .build();
    }

    static public Grade fromUpdateGrade(
            UpdateGradeRequest updateGradeRequest,
            Grade grade,
            float diemTK
    ) {
        return Grade.builder()
                .gradeId(grade.gradeId)
                .student(grade.student)
                .classSubject(grade.classSubject)
                .diemCC(updateGradeRequest.getDiemCC())
                .diemBT(updateGradeRequest.getDiemBT())
                .diemTH(updateGradeRequest.getDiemTH())
                .diemKT(updateGradeRequest.getDiemKT())
                .diemCK(updateGradeRequest.getDiemCK())
                .diemTK(diemTK)
                .status(updateGradeRequest.getStatus())
                .build();
    }
}