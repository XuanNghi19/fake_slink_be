package com.example.fake_Slink.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewFormID;

    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subjectID")
    private Subject subject;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name = "response")
    private String response;

}
