package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.enums.ReviewFormStatus;
import com.example.fake_Slink.models.ReviewForm;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReviewFormResponse {
    int classSubjectId;

    String subjectName;

    int credits;

    ReviewFormStatus status;

    static public ReviewFormResponse fromReviewForm(ReviewForm reviewForm) {
        return ReviewFormResponse.builder()
                .classSubjectId(reviewForm.getClassSubject().getClassSubjectID())
                .subjectName(reviewForm.getClassSubject().getSubject().getSubjectName())
                .credits(reviewForm.getClassSubject().getSubject().getCredits())
                .status(reviewForm.getStatus())
                .build();
    }
}
