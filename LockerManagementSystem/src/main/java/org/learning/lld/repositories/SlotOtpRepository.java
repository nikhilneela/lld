package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Slot;

import java.util.HashMap;
import java.util.Map;

public class SlotOtpRepository implements ISlotOtpRepository {
    private final Map<String, String> slotToOtpMap;

    public SlotOtpRepository() {
        slotToOtpMap = new HashMap<>();
    }

    @Override
    public void addOtp(@NonNull Slot slot, @NonNull String otp) {
        slotToOtpMap.put(slot.getId(), otp);
    }

    @Override
    public String getOtp(@NonNull Slot slot) {
        return slotToOtpMap.get(slot.getId());
    }
}
