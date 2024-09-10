package com.example.fake_Slink.services;

import com.example.fake_Slink.dtos.requests.CreateMajorRequest;

import java.util.List;

public interface MajorService {
    boolean addMajors(List<CreateMajorRequest> list) throws Exception;
}
