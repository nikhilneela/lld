package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Slot {
    @Getter
    private final Integer number;
    private final String id;
    @Getter
    @Setter
    private IVehicle vehicle;

    public Slot(@NonNull final Integer number, @NonNull final String id) {
        this.number = number;
        this.id = id;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public void assignVehicle(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void vacate() {
        this.vehicle = null;
    }
}
