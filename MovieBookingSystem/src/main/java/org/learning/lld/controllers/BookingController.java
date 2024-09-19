package org.learning.lld.controllers;

import lombok.NonNull;
import org.learning.lld.models.Seat;
import org.learning.lld.models.Show;
import org.learning.lld.models.User;
import org.learning.lld.services.BookingService;
import org.learning.lld.services.ShowService;
import org.learning.lld.services.TheatreService;
import org.learning.lld.services.UserService;

import java.util.List;

public class BookingController {
    private final BookingService bookingService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final UserService userService;

    public BookingController(@NonNull final BookingService bookingService, @NonNull final ShowService showService, @NonNull final TheatreService theatreService, @NonNull final UserService userService) {
        this.bookingService = bookingService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.userService = userService;
    }

    public String createBooking(@NonNull final String userId, @NonNull final String showId, @NonNull final List<String> seatIds) {
        final Show show = this.showService.getShow(showId);
        final List<Seat> seats = this.theatreService.getSeats(seatIds);
        final User user = this.userService.getUser(userId);
        return bookingService.createBooking(user, show, seats).getId();
    }
}
