package com.example.fake_Slink.dtos.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReviewFormRequest {
    String studentIdNum;
    
    int classSubjectID;
    
    String content;
    
    String attachedFile;
}
