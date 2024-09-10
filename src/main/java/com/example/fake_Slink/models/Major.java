package com.example.fake_Slink.models;

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
}
