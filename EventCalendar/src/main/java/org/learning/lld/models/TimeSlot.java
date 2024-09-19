package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class TimeSlot {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private TimeSlot(@NonNull final LocalDateTime startTime, @NonNull final LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimeSlot of(@NonNull final LocalDateTime startTime, @NonNull final LocalDateTime endTime) {
        return new TimeSlot(startTime, endTime);
    }

    public boolean doesOverlap(@NonNull final TimeSlot timeSlot) {
        return !timeSlot.endTime.isBefore(startTime) && !timeSlot.startTime.isAfter(endTime);
    }

}
