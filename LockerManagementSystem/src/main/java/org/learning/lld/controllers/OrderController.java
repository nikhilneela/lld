package org.learning.lld.controllers;

import lombok.NonNull;
import org.learning.lld.models.Buyer;
import org.learning.lld.models.LockerItem;
import org.learning.lld.models.Slot;
import org.learning.lld.services.LockerService;
import org.learning.lld.services.NotificationService;
import org.learning.lld.services.OtpService;

public class OrderController {
    private final LockerService lockerService;
    private final NotificationService notificationService;
    private final OtpService otpService;

    public OrderController(@NonNull final LockerService lockerService,
                           @NonNull final NotificationService notificationService,
                           @NonNull final OtpService otpService) {
        this.lockerService = lockerService;
        this.notificationService = notificationService;
        this.otpService = otpService;
    }

    public Slot allocateLocker(@NonNull final Buyer buyer, @NonNull final LockerItem lockerItem) {
        Slot assignedSlot = lockerService.allocateLockerItem(lockerItem);
        final String otp = otpService.generateOtp(assignedSlot);
        notificationService.notify(buyer, otp, assignedSlot);
        return assignedSlot;
    }
}
