package org.learning.lld.strategies;

import org.learning.lld.models.Slot;

import java.util.List;

public interface ISlotAssignmentStrategy {
    Slot assignSlot(List<Slot> slots);
}
