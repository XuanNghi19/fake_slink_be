package com.example.fake_Slink.dtos.requests;

import com.example.fake_Slink.enums.Role;
import com.example.fake_Slink.models.DraftStudentNum;
import com.example.fake_Slink.models.Major;
import com.example.fake_Slink.models.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStudentRequest {
    String course;
    String password;
    String name;
    String cccd;
    Date dob;
    String email;
    String phone1;
    String phone2;
    String sex;
    String address;
    String majorId;
}
