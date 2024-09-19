package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Slot;

public interface ISlotOtpRepository {
    void addOtp(@NonNull final Slot slot, @NonNull final String otp);
    String getOtp(@NonNull final Slot slot);
}
