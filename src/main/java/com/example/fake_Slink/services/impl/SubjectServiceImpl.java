package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateSubjectRequest;
import com.example.fake_Slink.models.Subject;
import com.example.fake_Slink.repositories.SubjectRepository;
import com.example.fake_Slink.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    @Override
    public boolean addSubject(List<CreateSubjectRequest> list) throws Exception {
        for (var x : list) {
            if(subjectRepository.existsByIdNum(x.getIdNum())) {
                throw new RuntimeException("idNum da ton tai");
            }
            Subject newSubject = Subject.fromCreateSubjectRequest(x);
            subjectRepository.save(newSubject);
        }
        return true;
    }
}
