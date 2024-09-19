package org.learning.lld.strategies;

import org.learning.lld.models.LockerItem;
import org.learning.lld.models.Slot;

import java.util.List;
import java.util.stream.Collectors;

public class MatchingSizeSlotFilteringStrategy implements ISlotFilteringStrategy {
    @Override
    public List<Slot> filterSlots(final List<Slot> slots, final LockerItem lockerItem) {
        return slots.stream().filter(slot -> slot.getSize().fitsIn(lockerItem.getSize())).collect(Collectors.toList());
    }
}
