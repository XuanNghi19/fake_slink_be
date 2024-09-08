package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.configs.security.JwtUtils;
import com.example.fake_Slink.dtos.requests.*;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.IntrospectResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;
import com.example.fake_Slink.enums.Role;
import com.example.fake_Slink.models.DraftStudentNum;
import com.example.fake_Slink.models.Major;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.Teacher;
import com.example.fake_Slink.repositories.*;
import com.example.fake_Slink.services.StudentServices;
import com.nimbusds.jwt.SignedJWT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServicesImpl implements StudentServices {

    private final JwtUtils jwtUtils;
    private final StudentRepositories studentRepositories;
    private final TeacherRepositories teacherRepositories;
    private final PasswordEncoder passwordEncoder;
    private final DraftStudentNumRepositories draftStudentNumRepositories;
    private final MajorRepositories majorRepositories;

    @Transactional
    @Override
    public Boolean addStudents(List<CreateStudentRequest> list) throws Exception {
        List<Student> studentList;
        for (var x : list) {
            String newNum = "";
            Major major = majorRepositories.findById(x.getMajorId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay majorId: " + x.getMajorId()));
            DraftStudentNum draftStudentNum = draftStudentNumRepositories.findByCourseAndMajor(x.getCourse(), major);
            Integer trashNum = draftStudentNum.getStudentNum();

            if (trashNum < 10) {
                newNum = "00" + trashNum.toString();
            } else if (trashNum < 100) {
                newNum = "0" + trashNum.toString();
            } else {
                newNum = trashNum.toString();
            }

            String newIdNum = "D" + x.getCourse() + x.getMajorId() + newNum;
            if(studentRepositories.existsByIdNum(newIdNum)) {
                throw new RuntimeException("idNum da ton tai");
            }
            draftStudentNum.setStudentNum(draftStudentNum.getStudentNum() + 1);
            draftStudentNumRepositories.save(draftStudentNum);
            String encodePassword = passwordEncoder.encode(x.getPassword());

            Student newStudent = Student.fromCreateStudentRequest(x, newIdNum, encodePassword, major);
            studentRepositories.save(newStudent);
        }
        return true;
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequests) throws Exception {
        Student existingStudent = studentRepositories.findByIdNum(authenticationRequests.getIdNum())
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + authenticationRequests.getIdNum()));

        if (passwordEncoder.matches(authenticationRequests.getPassword(), existingStudent.getPassword())) {
            String token = jwtUtils.generateToken(existingStudent);
            return AuthenticationResponse.builder()
                    .token(token)
                    .authenticated(true)
                    .build();
        }
        throw new Exception("Thong tin dang nhap khong chinh xac");
    }

    @Override
    public StudentResponse getStudentDetail(IntrospectRequest introspectRequest) throws Exception {

        IntrospectResponse introspectResponse = jwtUtils.introspect(introspectRequest);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(introspectRequest.getToken());
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay sinh vien voi idNum: " + idNum));

        return StudentResponse.fromStudent(student);
    }

    @Override
    public List<StudentResponse> getStudentList(IntrospectRequest introspectRequest) throws Exception {
        IntrospectResponse introspectResponse = jwtUtils.introspect(introspectRequest);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(introspectRequest.getToken());
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Teacher admin = teacherRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay admin voi idNum: " + idNum));

        if(admin.getRole() == Role.ADMIN){
            return studentRepositories.findAll()
                    .stream().map(StudentResponse::fromStudent)
                    .toList();
        } else {
            throw new Exception("Khong phai la Admin");
        }
    }

    @Override
    public StudentResponse updateStudent(UpdateStudentRequest updateStudentRequest) throws Exception {

        IntrospectResponse introspectResponse = jwtUtils.introspect(updateStudentRequest.getIntrospectRequest());
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(updateStudentRequest.getIntrospectRequest().getToken());
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay sinh vien voi idNum: " + idNum));

        student = Student.fromUpdateStudentRequest(updateStudentRequest, student);

        return StudentResponse.fromStudent(studentRepositories.save(student));
    }

    @Override
    public Boolean updatePassword(UpdatePasswordRequest updatePasswordRequest) throws Exception {

        IntrospectResponse introspectResponse = jwtUtils.introspect(updatePasswordRequest.getIntrospectRequest());
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(updatePasswordRequest.getIntrospectRequest().getToken());
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay sinh vien voi idNum: " + idNum));

        String encodePassword = passwordEncoder.encode(updatePasswordRequest.getPassword());

        student.setPassword(encodePassword);

        studentRepositories.save(student);
        return true;
    }
}
