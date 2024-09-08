package com.example.fake_Slink.models;

import com.example.fake_Slink.models.embeddable.SubjectRegistrationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectRegistration {
    @EmbeddedId
    private SubjectRegistrationId subjectRegistrationId;

    @ManyToOne
    @MapsId("studentID")
    @JoinColumn(name = "studentID")
    private Student student;

    @ManyToOne
    @MapsId("classSubjectID")
    @JoinColumn(name = "classSubjectID")
    private ClassSubject classSubject;
}