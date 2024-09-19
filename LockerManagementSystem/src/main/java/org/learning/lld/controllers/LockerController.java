package org.learning.lld.controllers;

import lombok.NonNull;
import org.learning.lld.models.Locker;
import org.learning.lld.models.LockerUser;
import org.learning.lld.models.Slot;
import org.learning.lld.services.LockerService;
import org.learning.lld.services.OtpService;

import java.util.List;

public class LockerController {
    private final LockerService lockerService;
    private final OtpService otpService;

    public LockerController(@NonNull final LockerService lockerService, @NonNull final OtpService otpService) {
        this.lockerService = lockerService;
        this.otpService = otpService;
    }

    public Locker createLocker(@NonNull final String id) {
        return this.lockerService.createLocker(id);
    }

    public boolean unlockSlot(@NonNull final String otp, @NonNull final Slot slot) {
        if (this.otpService.validateOtp(otp, slot)) {
            System.out.println("Locker Slot = " + slot.getId() + " opened successfully as otp matched");
            return true;
        }
        return false;
    }

    public List<Slot> getAvailableSlots() {
        return this.lockerService.getAvailableSlots();
    }

    public void deAllocateSlot(@NonNull final Slot slot) {
        this.lockerService.deallocateLockerItem(slot);
    }
}
