package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.GradeNotificationRequest;
import com.example.fake_Slink.dtos.requests.UpdateGradeRequest;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;
import com.example.fake_Slink.dtos.responses.GradeResponse;
import com.example.fake_Slink.dtos.responses.LearningOutcomesResponse;
import com.example.fake_Slink.dtos.responses.SemesterResponse;
import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.Grade;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.StudentDevice;
import com.example.fake_Slink.models.embeddable.GradeId;
import com.example.fake_Slink.repositories.GradeRepository;
import com.example.fake_Slink.repositories.StudentDeviceRepository;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.services.FCMService;
import com.example.fake_Slink.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final StudentDeviceRepository studentDeviceRepository;
    private final FCMService fcmService;

    @Override
    public Boolean updateGradeFromSubjectClass(List<UpdateGradeRequest> list) throws Exception {

        for(var x : list) {
            Student student = studentRepository.findByIdNum(x.getStudentIdNum())
                    .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + x.getStudentIdNum()));
            GradeId gradeId = new GradeId(student.getId(), x.getClassSubjectID());
            Grade grade = gradeRepository.findById(gradeId)
                    .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi gradeId: " + gradeId));

            float diemTK = 0f;
            Date appealsDateLine = null;
            if(
                    x.getDiemCC() != 0f
                    && x.getDiemBT() != 0f
                    && x.getDiemKT() != 0f
                    && x.getDiemTH() != 0f
                    && x.getDiemCK() != 0f
            ) {
                ClassSubject classSubject = grade.getClassSubject();
                diemTK = ((x.getDiemCC()*classSubject.getPercentDiemCC() / 100)
                        + (x.getDiemBT()*classSubject.getPercentDiemBT() / 100)
                        + (x.getDiemKT()*classSubject.getPercentDiemKT() / 100)
                        + (x.getDiemTH()*classSubject.getPercentDiemTH() / 100)
                        + (x.getDiemCK()*classSubject.getPercentDiemCK() / 100)
                );

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, 15);
                appealsDateLine = calendar.getTime();
            }

            grade = Grade.fromUpdateGrade(x, grade, diemTK, appealsDateLine);
            gradeRepository.save(grade);

            GradeNotificationRequest notificationRequest = GradeNotificationRequest.fromGradeResponse(
                    GradeResponse.fromGrade(grade)
            );
            List<StudentDevice> deviceList = studentDeviceRepository.findByStudent(student);

            for(var device : deviceList) {
                notificationRequest.setFcmToken(device.getFcmToken());
                fcmService.sendGradedNotification(notificationRequest);
            }
        }

        return true;
    }

    @Override
    public LearningOutcomesResponse getAllGradeFromStudent(String idNum) throws Exception {
        Student student = studentRepository.findByIdNum(idNum)
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + idNum));

        List<GradeResponse> gradeResponseList = gradeRepository.findByStudent(student)
                .stream()
                .map(GradeResponse::fromGrade)
                .toList();

        List<SemesterResponse> semesterResponseList = gradeResponseList.stream()
                .map(gradeResponse -> gradeResponse.getClassSubjectResponse().getSemesterResponse())
                .distinct()
                .sorted(Comparator.comparing(SemesterResponse::getStartDate).reversed())
                .toList();

        return new LearningOutcomesResponse(semesterResponseList, gradeResponseList);
    }

    @Override
    public List<GradeResponse> getGradeAppealsList(String idNum) throws Exception {
        Student student = studentRepository.findByIdNum(idNum)
                .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + idNum));

        List<GradeResponse> gradeAppealsList = gradeRepository.findByStudentAndAppealsDatelineAfterToday(student)
                .stream()
                .map(GradeResponse::fromGrade)
                .toList();
        return gradeAppealsList;
    }
}
