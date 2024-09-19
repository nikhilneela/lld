package org.learning.lld.repositories;

import org.learning.lld.models.Locker;
import org.learning.lld.models.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

public class LockerRepository implements ILockerRepository {
    private List<Locker> lockers;

    public LockerRepository() {
        this.lockers = new ArrayList<>();
    }

    @Override
    public Locker createLocker(final String id) {
        Locker locker = new Locker(id);
        this.lockers.add(locker);
        return locker;
    }

    @Override
    public Locker getLocker(String id) {
        return this.lockers.stream().filter(locker -> locker.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Slot> getAvailableSlots() {
        List<Slot> availableSlots = new ArrayList<>();
        for (Locker locker : lockers) {
             availableSlots.addAll(locker.getAvailableSlots());
        }
        return availableSlots;
    }
}
