package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.Subject;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {
    int subjectID;
    
    String idNum;
    
    String subjectName;

    int credits;
    
    String describe;

    public static SubjectResponse fromSubject(Subject subject) {
        return SubjectResponse.builder()
                .subjectID(subject.getSubjectID())
                .subjectName(subject.getSubjectName())
                .credits(subject.getCredits())
                .describe(subject.getDescribe())
                .build();
    }
}
