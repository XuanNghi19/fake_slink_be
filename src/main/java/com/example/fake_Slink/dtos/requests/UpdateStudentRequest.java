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
public class UpdateStudentRequest {
    IntrospectRequest introspectRequest;
    String name;
    Date dob;
    String email;
    String cccd;
    String phone1;
    String phone2;
    String sex;
    String address;
}
