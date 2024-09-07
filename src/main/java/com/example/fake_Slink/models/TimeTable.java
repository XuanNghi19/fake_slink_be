package com.example.fake_Slink.models;

import com.example.fake_Slink.models.embeddable.TimeTableId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTable {
    @EmbeddedId
    private TimeTableId timeTableId;

    @ManyToOne
    @MapsId("classSubjectID")
    @JoinColumn(name = "classSubjectID", insertable = false, updatable = false)
    private ClassSubject classSubject;
}
