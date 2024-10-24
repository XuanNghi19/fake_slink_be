package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateSemesterRequest;
import com.example.fake_Slink.dtos.responses.SemesterResponse;
import com.example.fake_Slink.models.Semester;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.repositories.SemesterRepository;
import com.example.fake_Slink.services.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;
    @Override
    public boolean createSemester(CreateSemesterRequest request) throws Exception {
        Semester newSemester = Semester.fromCreateSemesterRequest(request);
        semesterRepository.save(newSemester);
        return true;
    }

    @Override
    public SemesterResponse getSemester(int idSemester) throws Exception{
        Semester existingSemester = semesterRepository.findById(idSemester)
                .orElseThrow(() -> new Exception("Khong tim thay nam hoc voi id: " + idSemester));

        return SemesterResponse.fromSemester(existingSemester);
    }

    @Override
    public List<SemesterResponse> getAllSemester() throws Exception {
        return semesterRepository.findAll().stream()
                .map(SemesterResponse::fromSemester)
                .collect(Collectors.toList());
    }
}
