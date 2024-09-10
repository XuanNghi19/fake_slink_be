package com.example.fake_Slink.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "draft_student_num")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DraftStudentNum {
    @Id
    @Column(name = "course")
    private String course;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(name = "student_num")
    private int studentNum;
}
