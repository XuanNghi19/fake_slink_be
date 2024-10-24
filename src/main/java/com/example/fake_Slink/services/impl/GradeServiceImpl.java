package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.UpdateGradeRequest;
import com.example.fake_Slink.dtos.responses.ClassSubjectResponse;
import com.example.fake_Slink.dtos.responses.GradeResponse;
import com.example.fake_Slink.dtos.responses.LearningOutcomesResponse;
import com.example.fake_Slink.dtos.responses.SemesterResponse;
import com.example.fake_Slink.models.ClassSubject;
import com.example.fake_Slink.models.Grade;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.embeddable.GradeId;
import com.example.fake_Slink.repositories.GradeRepository;
import com.example.fake_Slink.repositories.StudentRepository;
import com.example.fake_Slink.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Override
    public Boolean updateGradeFromSubjectClass(List<UpdateGradeRequest> list) throws Exception {

        for(var x : list) {
            Student student = studentRepository.findByIdNum(x.getStudentIdNum())
                    .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi idNum: " + x.getStudentIdNum()));
            GradeId gradeId = new GradeId(student.getId(), x.getClassSubjectID());
            Grade grade = gradeRepository.findById(gradeId)
                    .orElseThrow(() -> new Exception("Khong tim thay sinh vien voi gradeId: " + gradeId));

            float diemTK = 0f;

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
            }

            grade = Grade.fromUpdateGrade(x, grade, diemTK);
            gradeRepository.save(grade);
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

        List<SemesterResponse> semesterResponseList = new ArrayList<>();

        for(var x : gradeResponseList) {
            ClassSubjectResponse classSubjectResponse = x.getClassSubjectResponse();
            if(!semesterResponseList.contains(classSubjectResponse.getSemesterResponse())) {
                semesterResponseList.add(classSubjectResponse.getSemesterResponse());
            }
        }

        Collections.sort(semesterResponseList, new Comparator<SemesterResponse>() {
            @Override
            public int compare(SemesterResponse o1, SemesterResponse o2) {
                return o2.getStartDate().compareTo(o1.getStartDate());
            }
        });

        return new LearningOutcomesResponse(semesterResponseList, gradeResponseList);
    }
}