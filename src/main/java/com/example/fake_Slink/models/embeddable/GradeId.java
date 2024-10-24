package com.example.fake_Slink.models.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeId implements Serializable {
    private int studentID;
    private int classSubjectID;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return  true;
        if(obj == null || getClass() != obj.getClass()) return false;
        GradeId that = (GradeId) obj;

        return studentID == that.studentID &&
                classSubjectID == that.classSubjectID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, classSubjectID);
    }

    static public GradeId fromCreateClassSubjectRequest(
            int studentID,
            int classSubjectID
    ) {
        return GradeId.builder()
                .studentID(studentID)
                .classSubjectID(classSubjectID)
                .build();
    }
}
