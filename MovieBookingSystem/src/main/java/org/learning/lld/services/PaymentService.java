package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.BadRequestException;
import org.learning.lld.models.Booking;
import org.learning.lld.models.User;
import org.learning.lld.providers.ISeatLockProvider;

public class PaymentService {
    private final ISeatLockProvider seatLockProvider;

    public PaymentService(@NonNull final ISeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
    }

    //this is not about processing refunds, but freeing up locked seats due to failed payment
    public void processFailedPayment(@NonNull Booking booking, @NonNull final User user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }
        seatLockProvider.unlockSeats(booking.getShow(), booking.getSeats(), user);
    }
}
