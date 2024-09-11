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

    @Column(name = "major_name")
    private String majorName;

    public static Major fromCreateMajorRequest(CreateMajorRequest request) {
        return Major.builder()
                .id(request.getId())
                .majorName(request.getMajorName())
                .build();
    }
}
