import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.learning.lld.models.TimeSlot;
import org.learning.lld.models.User;
import org.learning.lld.models.WorkingHours;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class LocalTimeTests {

    @Test
    public void understandLocalTime() {
        LocalTime start = LocalTime.of(10, 30);
        LocalTime end = LocalTime.of(15, 30);

        System.out.println(start.isBefore(end));
    }

    @Test
    public void checkUserAvailability() {
        DateTimeFormatter formatterForTimeSlot = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        DateTimeFormatter formatterForWorkingHours = DateTimeFormatter.ofPattern("hh:mm a");


        User user = new User(
                 UUID.randomUUID().toString(),
                "xyz",
                new WorkingHours(LocalTime.parse("08:00 AM", formatterForWorkingHours), LocalTime.parse("06:00 PM", formatterForWorkingHours))
        );

        TimeSlot timeSlot = TimeSlot.of(
                LocalDateTime.parse("18-09-2024 10:30 AM", formatterForTimeSlot),
                LocalDateTime.parse("18-09-2024 05:30 PM", formatterForTimeSlot)
        );

        Assertions.assertTrue(user.isAvailable(timeSlot));

        TimeSlot timeSlot1 = TimeSlot.of(
                LocalDateTime.parse("18-09-2024 07:30 AM", formatterForTimeSlot),
                LocalDateTime.parse("18-09-2024 05:30 PM", formatterForTimeSlot)
        );

        Assertions.assertFalse(user.isAvailable(timeSlot1));

    }
}
