package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class TimeSlot implements Comparable<TimeSlot> {
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

    public boolean overlaps(@NonNull final TimeSlot other) {
        return (this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime));
    }

    public boolean endBefore(@NonNull final TimeSlot o) {
        return this.getEndTime().isBefore(o.getStartTime());
    }

    public boolean startsAfter(@NonNull final TimeSlot o) {
        return this.getStartTime().isAfter(o.getEndTime());
    }

    @Override
    public int compareTo(@NonNull final TimeSlot o) {
        return this.startTime.compareTo(o.startTime);
    }
}
