package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.dtos.responses.GradeResponse;
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
public class GradeNotificationRequest {
    String fcmToken;

    String classify;

    GradeResponse gradeResponse;

    String title;

    String message;

    Date createAt;

    static public GradeNotificationRequest fromGradeResponse(
            GradeResponse gradeResponse
    ) {
        SubjectResponse subjectResponse = gradeResponse.getClassSubjectResponse().getSubjectResponse();
        String message = "Thông báo! Học phần " + subjectResponse.getSubjectName() + " đã được cập nhật điểm.";
        return GradeNotificationRequest.builder()
                .classify("grade")
                .gradeResponse(gradeResponse)
                .title("Cập nhật điểm học phần!")
                .message(message)
                .createAt(new Date())
                .build();
    }
}
