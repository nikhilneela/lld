package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Getter
public class Booking {
    private final String id;
    private final User user;
    private final Show show;
    private final List<Seat> seats;
    private BookingStatus status;

    public Booking(@NonNull final User user, @NonNull final List<Seat> seats, @NonNull final Show show) {
        this.id = UUID.randomUUID().toString();
        this.seats = seats;
        this.show = show;
        this.status = BookingStatus.CREATED;
        this.user = user;
    }

    public void confirmBooking() {
        this.status = BookingStatus.CONFIRMED;
    }
}
