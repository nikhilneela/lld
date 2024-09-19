package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class User {
    private final String id;
    private final String name;
    private final WorkingHours workingHours;

    public boolean isAvailable(@NonNull final TimeSlot timeSlot) {
        LocalTime slotStartTime = timeSlot.getStartTime().toLocalTime();
        LocalTime slotEndTime = timeSlot.getEndTime().toLocalTime();

        return !slotStartTime.isBefore(workingHours.getStartTime()) && !slotStartTime.isAfter(workingHours.getEndTime())
                && !slotEndTime.isAfter(workingHours.getEndTime());
    }
}
