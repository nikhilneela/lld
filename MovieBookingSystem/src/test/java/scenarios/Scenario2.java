package scenarios;

import org.junit.Test;

import java.util.List;

public class Scenario2 extends BaseTest {
    @Test
    public void validateScenario2() {
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
        String u1BookingId = this.bookingController.createBooking(u1, showId, u1SelectedSeats);
        List<String> showAvailableSeatsAfterU1Selection = this.showController.getAvailableSeats(showId);
        validateSeatsAreExcluded(u1SelectedSeats, showAvailableSeatsAfterU1Selection);
        this.paymentController.paymentFailed(u1BookingId, u1);
        List<String> showAvailableSeatsAfterU1PaymentFailed = this.showController.getAvailableSeats(showId);
        validateSeats(showAvailableSeatsAfterU1PaymentFailed, u1SelectedSeats);
    }
}
