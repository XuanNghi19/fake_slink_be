package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateMajorRequest;
import com.example.fake_Slink.dtos.requests.CreateSubjectRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectID;

    @Column(name = "id_num")
    private String idNum;

    @Column(name = "subjectName")
    private String subjectName;

    @Column(name = "credits")
    private int credits;

    @Column(name = "describe")
    private String describe;

    public static Subject fromCreateSubjectRequest(CreateSubjectRequest request) {
        return Subject.builder()
                .idNum(request.getIdNum())
                .subjectName(request.getSubjectName())
                .credits(request.getCredits())
                .describe(request.getDescribe())
                .build();
    }
}


