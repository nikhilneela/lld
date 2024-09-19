package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.BadRequestException;
import org.learning.lld.exceptions.NoSuchBookingException;
import org.learning.lld.exceptions.SeatsAlreadyBookedException;
import org.learning.lld.exceptions.SeatsUnavailableException;
import org.learning.lld.models.*;
import org.learning.lld.providers.ISeatLockProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingService {
    private final Map<String, Booking> bookings;
    private final ISeatLockProvider seatLockProvider;

    public BookingService(@NonNull final ISeatLockProvider seatLockProvider) {
        this.bookings = new HashMap<>();
        this.seatLockProvider = seatLockProvider;
    }

    public Booking createBooking(@NonNull final User user, @NonNull final Show show, @NonNull final List<Seat> seats) {
        if (!isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatsAlreadyBookedException();
        }
        this.seatLockProvider.lockSeats(show, seats, user);
        Booking booking = new Booking(user, seats, show);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking getBooking(@NonNull final String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            throw new NoSuchBookingException();
        }
        return bookings.get(bookingId);
    }

    public List<Seat> getUnavailableSeats(@NonNull final Show show) {
        final List<Booking> bookings = getAllBookings(show);
        return bookings.stream()
                .filter(this::isBookingConfirmed)
                .flatMap(booking -> booking.getSeats().stream())
                .collect(Collectors.toList());
    }

    private boolean isAnySeatAlreadyBooked(@NonNull final Show show, @NonNull final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat: seats) {
            if (bookedSeats.contains(seat)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAnySeatUnavailable(@NonNull final Show show, @NonNull final List<Seat> seats) {
        final List<Seat> unavailableSeats = getUnavailableSeats(show);
        for (Seat seat: seats) {
            if (unavailableSeats.contains(seat)) {
                return false;
            }
        }
        return true;
    }

    private List<Seat> getBookedSeats(@NonNull final Show show) {
        final List<Booking> bookings = getAllBookings(show);
        return bookings.stream()
                .filter(this::isBookingConfirmed)
                .flatMap(booking -> booking.getSeats().stream())
                .collect(Collectors.toList());
    }

    private List<Booking> getAllBookings(@NonNull final Show show) {
        return this.bookings.values().stream().filter(booking -> booking.getShow().getId().equals(show.getId())).collect(Collectors.toList());
    }

    private boolean isBookingConfirmed(@NonNull final Booking booking) {
        return booking.getStatus() == BookingStatus.CONFIRMED;
    }

    private boolean isBookingInProgress(@NonNull final Booking booking) {
        return booking.getStatus() == BookingStatus.CREATED;
    }

    public void confirmBooking(@NonNull final Booking booking, @NonNull final User user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }
        this.seatLockProvider.unlockSeats(booking.getShow(), booking.getSeats(), user);
        booking.confirmBooking();
    }
}
