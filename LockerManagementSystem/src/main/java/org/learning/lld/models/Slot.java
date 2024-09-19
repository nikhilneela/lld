package org.learning.lld.models;

import lombok.Getter;
import org.learning.lld.exceptions.SlotInUseException;

import java.util.Date;

public class Slot {
    @Getter
    private final String id;
    @Getter
    private final Size size;
    private LockerItem currentLockerItem;
    private Date allocatedDate;

    public Slot(final String slotId, final Size size) {
        this.id = slotId;
        this.size = size;
    }

    public boolean isAvailable() {
        return this.currentLockerItem == null;
    }

    public void assignLockerItem(LockerItem lockerItem) {
        if (this.currentLockerItem != null) {
            throw new SlotInUseException();
        }
        if (this.size.fitsIn(lockerItem.getSize())) {
            this.currentLockerItem = lockerItem;
            this.allocatedDate = new Date();
        }
    }

    public void deAllocateLockerItem() {
        this.currentLockerItem = null;
        this.allocatedDate = null;
    }
}
