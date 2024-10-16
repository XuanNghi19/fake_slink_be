package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.enums.Role;
import com.example.fake_Slink.models.Student;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentResponse {
    String course;
    String idNum;
    String name;
    Date dob;
    String email;
    String cccd;
    String phone1;
    String phone2;
    String sex;
    String address;
    String major;
    float gpa;
    public static StudentResponse fromStudent(Student student) {
        return StudentResponse.builder()
                .course(student.getCourse())
                .idNum(student.getIdNum())
                .name(student.getName())
                .dob(student.getDob())
                .email(student.getEmail())
                .cccd(student.getCccd())
                .phone1(student.getPhone1())
                .phone2(student.getPhone2())
                .sex(student.getSex())
                .address(student.getAddress())
                .major(student.getMajor().getMajorName())
                .gpa(student.getGpa())
                .build();
    }
}
