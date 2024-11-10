package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.dtos.responses.ReviewFormResponse;
import com.example.fake_Slink.dtos.responses.SubjectResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewFormNotificationRequest {
    String fcmToken;

    String classify;

    ReviewFormResponse reviewFormResponse;

    String title;

    String message;

    Date createAt;


    static public ReviewFormNotificationRequest fromReviewFormResponse(
            ReviewFormResponse response,
            SubjectResponse subjectResponse
    ) {
        String message = "Thông báo! Đơn phúc khảo học phần " + subjectResponse.getSubjectName() + " đã được cập nhật.";
        return ReviewFormNotificationRequest.builder()
                .classify("reviewForm")
                .reviewFormResponse(response)
                .title("Cập nhật đơn phúc khảo!")
                .createAt(new Date())
                .message(message)
                .build();
    }
}
