package com.example.fake_Slink.models.embeddable;

import com.example.fake_Slink.dtos.requests.CreateClassSubjectRequest;
import com.example.fake_Slink.dtos.requests.CreateSubjectRegistrationRequest;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    static public SubjectRegistrationId fromCreateClassSubjectRequest(
            int studentID,
            int classSubjectID
    ) {
        return SubjectRegistrationId.builder()
                .studentID(studentID)
                .classSubjectID(classSubjectID)
                .build();
    }
}
