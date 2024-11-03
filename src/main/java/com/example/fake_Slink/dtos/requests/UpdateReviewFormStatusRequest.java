package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.enums.ReviewFormStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateReviewFormStatusRequest {
    int reviewFormID;

    ReviewFormStatus status;
}
