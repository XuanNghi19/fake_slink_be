package com.example.fake_Slink.models.embeddable;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubjectRegistrationId implements Serializable {
    private int studentID;
    private int classSubjectID;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return  true;
        if(obj == null || getClass() != obj.getClass()) return false;
        SubjectRegistrationId that = (SubjectRegistrationId) obj;

        return studentID == that.studentID &&
                classSubjectID == that.classSubjectID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, classSubjectID);
    }
}
