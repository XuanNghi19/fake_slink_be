package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.configs.security.JwtUtils;
import com.example.fake_Slink.dtos.requests.AuthenticationRequest;
import com.example.fake_Slink.dtos.requests.CreateTeacherRequest;
import com.example.fake_Slink.dtos.requests.IntrospectRequest;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.TeacherResponse;
import com.example.fake_Slink.enums.Role;
import com.example.fake_Slink.models.*;
import com.example.fake_Slink.repositories.*;
import com.example.fake_Slink.services.TeacherServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServicesImpl implements TeacherServices {
    private final JwtUtils jwtUtils;
    private final TeacherRepositories teacherRepositories;
    private final PasswordEncoder passwordEncoder;
    private final DraftTeacherNumRepositories draftTeacherNumRepositories;
    private final DepartmentRepositories departmentRepositories;

    @Override
    public Boolean addTeachers(List<CreateTeacherRequest> list) throws Exception {
        List<Student> studentList;
        for (var x : list) {
            String newNum = "";
            Department department = departmentRepositories.findById(x.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay departmentId: " + x.getDepartmentId()));
            DraftTeacherNum draftTeacherNum = draftTeacherNumRepositories.findByDepartment(department);
            Integer trashNum = draftTeacherNum.getTeacherNum();

            if (trashNum < 10) {
                newNum = "000" + trashNum.toString();
            } else if (trashNum < 100) {
                newNum = "00" + trashNum.toString();
            } else if (trashNum < 1000) {
                newNum = "0" + trashNum.toString();
            } else {
                newNum = trashNum.toString();
            }

            String newIdNum = "GV" + x.getDepartmentId() + newNum;
            if(teacherRepositories.existsByIdNum(newIdNum)) {
                throw new RuntimeException("idNum da ton tai");
            }
            draftTeacherNum.setTeacherNum(draftTeacherNum.getTeacherNum() + 1);
            draftTeacherNumRepositories.save(draftTeacherNum);
            String encodePassword = passwordEncoder.encode(x.getPassword());

            Teacher newTeacher = Teacher.fromCreateTeacherRequest(x, newIdNum, encodePassword, department, Role.TEACHER);
            teacherRepositories.save(newTeacher);
        }
        return true;
    }

    @Override
    public Boolean addAdmin(CreateTeacherRequest admin) throws Exception {
        String newNum = "";
        Department department = departmentRepositories.findById(admin.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay departmentId: " + admin.getDepartmentId()));
        DraftTeacherNum draftTeacherNum = draftTeacherNumRepositories.findByDepartment(department);
        Integer trashNum = draftTeacherNum.getTeacherNum();

        if (trashNum < 10) {
            newNum = "000" + trashNum.toString();
        } else if (trashNum < 100) {
            newNum = "00" + trashNum.toString();
        } else if (trashNum < 1000) {
            newNum = "0" + trashNum.toString();
        } else {
            newNum = trashNum.toString();
        }

        String newIdNum = "GV" + admin.getDepartmentId() + newNum;
        if(teacherRepositories.existsByIdNum(newIdNum)) {
            throw new RuntimeException("idNum da ton tai");
        }
        draftTeacherNum.setTeacherNum(draftTeacherNum.getTeacherNum() + 1);
        draftTeacherNumRepositories.save(draftTeacherNum);
        String encodePassword = passwordEncoder.encode(admin.getPassword());

        Teacher newAdmin = Teacher.fromCreateTeacherRequest(admin, newIdNum, encodePassword, department, Role.ADMIN);
        teacherRepositories.save(newAdmin);

        return true;
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequests) throws Exception {
        Teacher existingTeacher = teacherRepositories.findByIdNum(authenticationRequests.getIdNum())
                .orElseThrow(() -> new Exception("Khong tim thay giao vien voi idNum: " + authenticationRequests.getIdNum()));

        if (passwordEncoder.matches(authenticationRequests.getPassword(), existingTeacher.getPassword())) {
            String token = jwtUtils.generateToken(existingTeacher);
            return AuthenticationResponse.builder()
                    .token(token)
                    .authenticated(true)
                    .build();
        }
        throw new Exception("Thong tin dang nhap khong chinh xac");
    }
}
