package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.configs.security.JwtUtils;
import com.example.fake_Slink.dtos.requests.*;
import com.example.fake_Slink.dtos.responses.AuthenticationResponse;
import com.example.fake_Slink.dtos.responses.IntrospectResponse;
import com.example.fake_Slink.dtos.responses.StudentResponse;
import com.example.fake_Slink.enums.Role;
import com.example.fake_Slink.models.*;
import com.example.fake_Slink.repositories.*;
import com.example.fake_Slink.services.FCMService;
import com.example.fake_Slink.services.StudentService;
import com.nimbusds.jwt.SignedJWT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final JwtUtils jwtUtils;
    private final StudentRepository studentRepositories;
    private final TeacherRepository teacherRepositories;
    private final PasswordEncoder passwordEncoder;
    private final DraftStudentNumRepository draftStudentNumRepositories;
    private final MajorRepository majorRepositories;
    private final FCMService fcmService;

    @Transactional
    @Override
    public Boolean addStudents(List<CreateStudentRequest> list) throws Exception {
        for (var x : list) {
            String newNum = "";
            Major major = majorRepositories.findById(x.getMajorId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay majorId: " + x.getMajorId()));
            DraftStudentNum draftStudentNum = draftStudentNumRepositories.findByCourseAndMajor(x.getCourse(), major);

            if(draftStudentNum == null) {
                DraftStudentNum newDraftStudentNum = DraftStudentNum.builder()
                        .course(x.getCourse())
                        .major(major)
                        .studentNum(1)
                        .build();
                draftStudentNumRepositories.save(newDraftStudentNum);
                draftStudentNum = draftStudentNumRepositories.findByCourseAndMajor(x.getCourse(), major);
            }

            Integer trashNum = draftStudentNum.getStudentNum();

            if (trashNum < 10) {
                newNum = "00" + trashNum.toString();
            } else if (trashNum < 100) {
                newNum = "0" + trashNum.toString();
            } else {
                newNum = trashNum.toString();
            }

            String newIdNum = "D" + x.getCourse() + major.getIdNum() + newNum;
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
    public AuthenticationResponse authenticationWithMobilePhone(
            AuthenticationWithMobilePhoneRequest authenticationRequests
    ) throws Exception {
        Student existingStudent = studentRepositories.findByIdNum(authenticationRequests.getIdNum())
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + authenticationRequests.getIdNum()));
        if (passwordEncoder.matches(authenticationRequests.getPassword(), existingStudent.getPassword())) {
            String token = jwtUtils.generateToken(existingStudent);

            fcmService.updateStudentDevice(
                    authenticationRequests.getUpdateStudentDeviceRequest()
            );

            return AuthenticationResponse.builder()
                    .token(token)
                    .authenticated(true)
                    .build();
        }
        throw new Exception("Thong tin dang nhap khong chinh xac");
    }

    @Override
    public StudentResponse getStudentDetail(String token) throws Exception {
        IntrospectResponse introspectResponse = jwtUtils.introspect(token);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }
        SignedJWT signedJWT = SignedJWT.parse(token);
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay sinh vien voi idNum: " + idNum));

        return StudentResponse.fromStudent(student);
    }
    @Override
    public List<StudentResponse> getStudentList(String token) throws Exception {
        IntrospectResponse introspectResponse = jwtUtils.introspect(token);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(token);
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
    public StudentResponse updateStudent(String token, UpdateStudentRequest updateStudentRequest) throws Exception {

        IntrospectResponse introspectResponse = jwtUtils.introspect(token);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(token);
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay sinh vien voi idNum: " + idNum));

        student = Student.fromUpdateStudentRequest(updateStudentRequest, student);

        return StudentResponse.fromStudent(studentRepositories.save(student));
    }
    @Override
    public Boolean updatePassword(String token, UpdatePasswordRequest updatePasswordRequest) throws Exception {

        IntrospectResponse introspectResponse = jwtUtils.introspect(token);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(token);
        String idNum = signedJWT.getJWTClaimsSet().getSubject();
        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new RuntimeException("Khong tim thay sinh vien voi idNum: " + idNum));

        if(passwordEncoder.matches(updatePasswordRequest.getOldPassword(), student.getPassword())) {

            String encodePassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());

            student.setPassword(encodePassword);

            studentRepositories.save(student);

            return true;
        }

        return false;
    }

    @Override
    public Boolean uploadAvatarUrl(UploadAvatarRequest request, String token) throws Exception {
        IntrospectResponse introspectResponse = jwtUtils.introspect(token);
        if(!introspectResponse.getValid()) {
            throw new RuntimeException("Token failed!");
        }

        SignedJWT signedJWT = SignedJWT.parse(token);
        String idNum = signedJWT.getJWTClaimsSet().getSubject();

        Student student = studentRepositories.findByIdNum(idNum)
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + idNum));
        student.setAvatarUrl(request.getAvatarUrl());
        studentRepositories.save(student);
        return true;
    }
}
