package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriversInMemoryRepository implements IDriversRepository {
    private final List<Driver> drivers;

    public DriversInMemoryRepository() {
        this.drivers = new ArrayList<>();
    }

    @Override
    public void registerDriver(@NonNull Driver driver) {
        this.drivers.add(driver);
    }

    @Override
    public Driver getDriver(@NonNull String id) {
        return this.drivers.stream().filter(driver -> driver.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return new ArrayList<>(this.drivers);
    }
}
