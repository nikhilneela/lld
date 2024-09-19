package org.learning.lld.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Locker {
    @Getter
    private String id;
    @Getter
    private List<Slot> slots;

    public Locker(final String id) {
        this.id = id;
        this.slots = new ArrayList<>();
    }

    public void addSlot(final Slot slot) {
        this.slots.add(slot);
    }

    public List<Slot> getAvailableSlots() {
        return this.slots.stream().filter(Slot::isAvailable).collect(Collectors.toList());
    }
}
