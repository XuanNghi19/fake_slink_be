package com.example.fake_Slink.models.embeddable;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TimeTableId implements Serializable {
    private int classSubjectID;
    private int dayOfWeek;
    private String startTime;
    private String endTime;

    public TimeTableId() {}

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return  true;
        if(obj == null || getClass() != obj.getClass()) return false;
        TimeTableId that = (TimeTableId) obj;

        return classSubjectID == that.classSubjectID &&
                dayOfWeek == that.dayOfWeek &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classSubjectID, dayOfWeek, startTime, endTime);
    }
}
