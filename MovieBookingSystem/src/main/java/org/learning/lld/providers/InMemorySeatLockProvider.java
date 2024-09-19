package org.learning.lld.providers;

import lombok.NonNull;
import org.learning.lld.exceptions.SeatsTemporarilyNotAvailableException;
import org.learning.lld.models.Seat;
import org.learning.lld.models.SeatLock;
import org.learning.lld.models.Show;
import org.learning.lld.models.User;

import java.util.*;
import java.util.stream.Collectors;

public class InMemorySeatLockProvider implements ISeatLockProvider {
    private final Map<Show, Map<Seat, SeatLock>> showToSeatLocksMap;
    private final Integer lockTimeoutInSeconds;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeoutInSeconds) {
        this.showToSeatLocksMap = new HashMap<>();
        this.lockTimeoutInSeconds = lockTimeoutInSeconds;
    }

    @Override
    public void lockSeats(@NonNull Show show, @NonNull List<Seat> seats, @NonNull User user) {
        //check if any of the seat is already locked
        if (seats.stream().anyMatch(seat -> isSeatLocked(show, seat))) {
            throw new SeatsTemporarilyNotAvailableException();
        }
        Map<Seat, SeatLock> seatLockMap = showToSeatLocksMap.getOrDefault(show, new HashMap<>());
        for (Seat seat : seats) {
            seatLockMap.put(seat, new SeatLock(seat, show, new Date(), this.lockTimeoutInSeconds, user));
        }
        showToSeatLocksMap.put(show, seatLockMap);
    }

    @Override
    public void unlockSeats(@NonNull Show show, @NonNull List<Seat> seats, @NonNull User user) {
        //todo: only the user who locked the seat should be able to unlock the seat
        Map<Seat, SeatLock> seatLockMap = this.showToSeatLocksMap.get(show);
        for (Seat seat : seats) {
            if (!validateLock(show, seat, user)) {
                throw new IllegalStateException();
            }
            seatLockMap.remove(seat);
        }
    }

    @Override
    public List<Seat> getLockedSeats(@NonNull Show show) {
        final List<Seat> lockedSeats = new ArrayList<>();
        if (!showToSeatLocksMap.containsKey(show)) {
            return lockedSeats;
        }
        Map<Seat, SeatLock> seatLockMap = this.showToSeatLocksMap.get(show);
        for (Seat seat : seatLockMap.keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean validateLock(@NonNull final Show show, @NonNull final Seat seat, @NonNull final User user) {
        return isSeatLocked(show, seat) && this.showToSeatLocksMap.get(show).get(seat).getLockedBy().equals(user);
    }

    private boolean isSeatLocked(@NonNull final Show show, @NonNull final Seat seat) {
        if (showToSeatLocksMap.containsKey(show)) {
            final Map<Seat, SeatLock> seatLockMap = showToSeatLocksMap.get(show);
            if (seatLockMap.containsKey(seat)) {
                final SeatLock seatLock = seatLockMap.get(seat);
                //if seat lock is NOT expired => someone is holding the lock => return true
                //if seat lock is expired => seat is free => return false
                return !seatLock.isLockExpired();
            }
            return false;
        }
        return false;
    }
}
