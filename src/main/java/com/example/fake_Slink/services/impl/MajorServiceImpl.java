package com.example.fake_Slink.services.impl;

import com.example.fake_Slink.dtos.requests.CreateMajorRequest;
import com.example.fake_Slink.models.Major;
import com.example.fake_Slink.repositories.MajorRepository;
import com.example.fake_Slink.services.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorServiceImpl implements MajorService {

    private final MajorRepository majorRepository;

    @Override
    public boolean addMajors(List<CreateMajorRequest> list) throws Exception {
        for (var x : list) {
            if(majorRepository.existsById(x.getId())) {
                throw new RuntimeException("id da ton tai");
            }
            Major newMajor = Major.fromCreateMajorRequest(x);
            majorRepository.save(newMajor);
        }
        return true;
    }
}
