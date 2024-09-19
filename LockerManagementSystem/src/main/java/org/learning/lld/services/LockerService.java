package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSlotsAvailableException;
import org.learning.lld.models.Locker;
import org.learning.lld.models.LockerItem;
import org.learning.lld.models.Slot;
import org.learning.lld.repositories.LockerRepository;
import org.learning.lld.strategies.ISlotAssignmentStrategy;
import org.learning.lld.strategies.ISlotFilteringStrategy;

import java.util.List;


public class LockerService {
    private final LockerRepository lockerRepository;
    private final ISlotFilteringStrategy slotFilteringStrategy;
    private final ISlotAssignmentStrategy slotAssignmentStrategy;

    public LockerService(@NonNull final LockerRepository lockerRepository,
                         @NonNull final ISlotFilteringStrategy slotFilteringStrategy,
                         @NonNull final ISlotAssignmentStrategy slotAssignmentStrategy) {
        this.lockerRepository = lockerRepository;
        this.slotFilteringStrategy = slotFilteringStrategy;
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }


    public Locker createLocker(@NonNull final String id) {
        return this.lockerRepository.createLocker(id);
    }

    public Slot allocateLockerItem(@NonNull final LockerItem lockerItem) {
        final List<Slot> allAvailableSlots = this.lockerRepository.getAvailableSlots();
        final List<Slot> slotsMatchingLockerItem = this.slotFilteringStrategy.filterSlots(allAvailableSlots, lockerItem);
        final Slot selectedSlot = this.slotAssignmentStrategy.assignSlot(slotsMatchingLockerItem);

        if (selectedSlot == null) {
            throw new NoSlotsAvailableException();
        }
        selectedSlot.assignLockerItem(lockerItem);
        return selectedSlot;
    }

    public void deallocateLockerItem(@NonNull final Slot slot) {
        slot.deAllocateLockerItem();
    }

    public List<Slot> getAvailableSlots() {
        return this.lockerRepository.getAvailableSlots();
    }
}
