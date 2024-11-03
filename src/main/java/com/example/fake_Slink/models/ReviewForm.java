package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateReviewFormRequest;
import com.example.fake_Slink.enums.ReviewFormStatus;
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
    @JoinColumn(name = "class_subject_id")
    private ClassSubject classSubject;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private ReviewFormStatus status;

    @Column(name = "attached_file")
    private String attachedFile;

    static public ReviewForm fromCreateReviewFormRequest(
            CreateReviewFormRequest request,
            Student student,
            ClassSubject classSubject
    ) {
        return ReviewForm.builder()
                .student(student)
                .classSubject(classSubject)
                .content(request.getContent())
                .status(ReviewFormStatus.SUBMITTED)
                .attachedFile(request.getAttachedFile())
                .build();
    }
}
