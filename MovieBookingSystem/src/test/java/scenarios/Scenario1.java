package scenarios;


import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Scenario1 extends BaseTest {



    @Test
    public void validateScenario1() {
        String screenId = setupScreen("PVR Gachibowli", "AUDI 1");
        List<String> allAvailableSeats = setupSeats(screenId, 5, 5);
        String showId = setupShow("bahubali", screenId);
        List<String> showAvailableSeats = this.showController.getAvailableSeats(showId);
        validateSeats(allAvailableSeats, showAvailableSeats);
        String u1 = this.userController.createUser("u1");
        List<String> u1SelectedSeats = List.of(
                allAvailableSeats.get(0),
                allAvailableSeats.get(5),
                allAvailableSeats.get(7),
                allAvailableSeats.get(9),
                allAvailableSeats.get(20)
        );
        String bookingId = this.bookingController.createBooking(u1, showId, u1SelectedSeats);
        List<String> showAvailableSeatsAfterU1Selection = this.showController.getAvailableSeats(showId);
        validateSeatsAreExcluded(u1SelectedSeats, showAvailableSeatsAfterU1Selection);
        this.paymentController.paymentSuccess(bookingId, u1);
    }

}
