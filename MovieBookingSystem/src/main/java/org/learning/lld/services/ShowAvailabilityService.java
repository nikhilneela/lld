package org.learning.lld.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.models.Seat;
import org.learning.lld.models.Show;
import org.learning.lld.providers.ISeatLockProvider;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ShowAvailabilityService {
    private final ISeatLockProvider seatLockProvider;
    private final BookingService bookingService;

    public ShowAvailabilityService(@NonNull final BookingService bookingService, @NonNull final ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(@NonNull final Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> bookedSeats = bookingService.getUnavailableSeats(show);
        final List<Seat> lockedSeats = seatLockProvider.getLockedSeats(show);
        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(bookedSeats);
        availableSeats.removeAll(lockedSeats);
        return availableSeats;
    }
}
