package org.learning.lld.strategies;

import org.learning.lld.models.Slot;

import java.util.List;

public class NearestToEntrySlotPickingStrategy implements ISlotPickingStrategy {
    @Override
    public Slot pickSlot(List<Slot> availableSlots) {
        return availableSlots.stream().filter(Slot::isAvailable).findFirst().orElse(null);
    }
}
