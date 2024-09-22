import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.learning.lld.models.TimeSlot;
import org.learning.lld.utils.TimeSlotUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeSlotTests {
    @Test
    public void freeSlotTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        List<TimeSlot> timeSlots = Arrays.asList(
                TimeSlot.of(LocalDateTime.parse("18-09-2024 10:30 AM", formatter), LocalDateTime.parse("18-09-2024 12:30 PM", formatter)),
                TimeSlot.of(LocalDateTime.parse("18-09-2024 09:00 PM", formatter), LocalDateTime.parse("18-09-2024 10:30 PM", formatter)),
                TimeSlot.of(LocalDateTime.parse("18-09-2024 03:00 PM", formatter), LocalDateTime.parse("18-09-2024 07:00 PM", formatter))
        );

        TimeSlot requiredSlot = TimeSlot.of(LocalDateTime.parse("18-09-2024 12:30 PM", formatter), LocalDateTime.parse("18-09-2024 03:00 PM", formatter));
        TimeSlot requiredSlot1 = TimeSlot.of(LocalDateTime.parse("18-09-2024 07:30 PM", formatter), LocalDateTime.parse("18-09-2024 09:00 PM", formatter));
        TimeSlot requiredSlot2 = TimeSlot.of(LocalDateTime.parse("18-09-2024 11:00 AM", formatter), LocalDateTime.parse("18-09-2024 12:35 PM", formatter));

        Assertions.assertTrue(TimeSlotUtils.isSlotAvailable(timeSlots, requiredSlot));
        Assertions.assertTrue(TimeSlotUtils.isSlotAvailable(timeSlots, requiredSlot1));
        Assertions.assertFalse(TimeSlotUtils.isSlotAvailable(timeSlots, requiredSlot2));
    }

    @Test
    public void freeSlotTestEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        List<TimeSlot> timeSlots = new ArrayList<>();

        TimeSlot requiredSlot = TimeSlot.of(LocalDateTime.parse("18-09-2024 12:30 PM", formatter), LocalDateTime.parse("18-09-2024 03:00 PM", formatter));
        TimeSlot requiredSlot1 = TimeSlot.of(LocalDateTime.parse("18-09-2024 07:30 PM", formatter), LocalDateTime.parse("18-09-2024 09:00 PM", formatter));
        TimeSlot requiredSlot2 = TimeSlot.of(LocalDateTime.parse("18-09-2024 11:00 AM", formatter), LocalDateTime.parse("18-09-2024 12:35 PM", formatter));

        Assertions.assertTrue(TimeSlotUtils.isSlotAvailable(timeSlots, requiredSlot));
        Assertions.assertTrue(TimeSlotUtils.isSlotAvailable(timeSlots, requiredSlot1));
        Assertions.assertTrue(TimeSlotUtils.isSlotAvailable(timeSlots, requiredSlot2));
    }

    @Test
    public void createTimeSlotWithSameStartAndEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        TimeSlot.of(LocalDateTime.parse("18-09-2024 12:30 PM", formatter), LocalDateTime.parse("18-09-2024 12:30 PM", formatter));
    }

    @Test
    public void createTimeSlotWithStartTimeGreaterAndEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TimeSlot.of(LocalDateTime.parse("18-09-2024 12:30 PM", formatter), LocalDateTime.parse("18-09-2024 12:29 PM", formatter));
        });
    }

    @Test
    public void overlaps() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        TimeSlot slot1 = TimeSlot.of(LocalDateTime.parse("18-09-2024 12:30 PM", formatter), LocalDateTime.parse("18-09-2024 03:30 PM", formatter));
        TimeSlot slot2 = TimeSlot.of(LocalDateTime.parse("18-09-2024 03:30 PM", formatter), LocalDateTime.parse("18-09-2024 04:30 PM", formatter));

        Assertions.assertFalse(slot1.overlaps(slot2));
    }
}
