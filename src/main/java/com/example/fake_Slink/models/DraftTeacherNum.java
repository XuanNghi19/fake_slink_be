package com.example.fake_Slink.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "draft_teacher_num")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DraftTeacherNum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "teacher_num")
    private int teacherNum;
}
