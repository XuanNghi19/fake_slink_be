package com.example.fake_Slink.dtos.responses;

import com.example.fake_Slink.models.Teacher;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TeacherResponse {
    String idNum;
    String name;
    Date dob;
    String email;
    String cccd;
    String phone1;
    String phone2;
    String sex;
    String address;
    String department;

    public static TeacherResponse fromTeacher(Teacher teacher) {
        return TeacherResponse.builder()
                .idNum(teacher.getIdNum())
                .name(teacher.getName())
                .dob(teacher.getDob())
                .email(teacher.getEmail())
                .cccd(teacher.getCccd())
                .phone1(teacher.getPhone1())
                .phone2(teacher.getPhone2())
                .sex(teacher.getSex())
                .address(teacher.getAddress())
                .department(teacher.getDepartment().getDepartmentName())
                .build();
    }
}
