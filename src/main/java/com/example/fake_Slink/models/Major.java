package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateMajorRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "major")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Major {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "id_num")
    private String idNum;

    @Column(name = "major_name")
    private String majorName;

    public static Major fromCreateMajorRequest(CreateMajorRequest request, Department department) {
        return Major.builder()
                .id(request.getId())
                .department(department)
                .idNum(request.getIdNum())
                .majorName(request.getMajorName())
                .build();
    }
}
