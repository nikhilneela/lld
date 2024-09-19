package org.learning.lld.repositories;

import org.learning.lld.models.Locker;
import org.learning.lld.models.Slot;

import java.util.List;

public interface ILockerRepository {
    Locker createLocker(final String id);
    Locker getLocker(final String id);

    List<Slot> getAvailableSlots();
}
