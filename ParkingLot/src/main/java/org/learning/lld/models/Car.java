package org.learning.lld.models;

import lombok.NonNull;

public class Car implements IVehicle {
    private final RegistrationNumber registrationNumber;
    private final Color color;

    public Car(@NonNull final RegistrationNumber registrationNumber, @NonNull final Color color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public RegistrationNumber getRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
