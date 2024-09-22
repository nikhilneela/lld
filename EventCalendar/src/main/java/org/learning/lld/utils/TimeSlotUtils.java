package org.learning.lld.utils;

import lombok.NonNull;
import org.learning.lld.models.TimeSlot;

import java.util.Comparator;
import java.util.List;

public class TimeSlotUtils {
    public static boolean isSlotAvailable(@NonNull final List<TimeSlot> timeSlots, @NonNull final TimeSlot requiredSlot) {
        timeSlots.sort(Comparator.comparing(TimeSlot::getStartTime));
        return timeSlots.stream().noneMatch(timeSlot -> timeSlot.overlaps(requiredSlot));
    }
}
