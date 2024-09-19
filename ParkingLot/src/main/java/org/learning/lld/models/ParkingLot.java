package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParkingLot {
    @Getter
    private final String id;
    private final List<Slot> slots;

    public ParkingLot(@NonNull final String id, int numberOfSlots) {
        this.id = id;
        this.slots = new ArrayList<>();

        for (int i = 0; i < numberOfSlots; i++) {
            Slot slot = new Slot(i + 1, UUID.randomUUID().toString());
            this.slots.add(slot);
        }
    }

    public List<Slot> getAvailableSlots() {
        return this.slots.stream().filter(Slot::isAvailable).collect(Collectors.toList());
    }

    public Slot getSlot(IVehicle vehicle) {
        return this.slots.stream().filter(slot -> slot.getVehicle().getRegistrationNumber().equals(vehicle.getRegistrationNumber())).findFirst().orElse(null);
    }

    public List<Slot> getSlotsWithVehicleColor(Color color) {
        return this.slots.stream().filter(slot -> !slot.isAvailable() && slot.getVehicle().getColor() == color).collect(Collectors.toList());
    }
}
