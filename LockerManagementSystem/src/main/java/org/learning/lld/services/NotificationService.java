package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.models.Buyer;
import org.learning.lld.models.LockerUser;
import org.learning.lld.models.Slot;

public class NotificationService {
    public void notify(@NonNull final LockerUser lockerUser, @NonNull final String otp, @NonNull final Slot slot) {
        System.out.println("Assigned slot = " + slot.getId() + " for user " + lockerUser.getId() + " with otp " + otp);
    }
}
