package com.example.fake_Slink.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
