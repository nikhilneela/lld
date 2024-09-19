package org.learning.lld.providers;

import lombok.NonNull;
import org.learning.lld.models.Seat;
import org.learning.lld.models.Show;
import org.learning.lld.models.User;

import java.util.List;

public interface ISeatLockProvider {
    void lockSeats(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final User user);
    void unlockSeats(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final User user);
    List<Seat> getLockedSeats(@NonNull final Show show);
}
