package org.learning.lld.strategies;

import org.learning.lld.models.LockerItem;
import org.learning.lld.models.Slot;

import java.util.List;

public interface ISlotFilteringStrategy {
    List<Slot> filterSlots(final List<Slot> slots, final LockerItem lockerItem);
}
