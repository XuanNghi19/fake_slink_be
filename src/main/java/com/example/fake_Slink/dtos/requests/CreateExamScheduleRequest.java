package com.example.fake_Slink.dtos.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateExamScheduleRequest {
    int classSubjectID;
    
    Date dateOfEvent;
    
    String location;

    String startTime;
    
    String minute;

    String format;
}
