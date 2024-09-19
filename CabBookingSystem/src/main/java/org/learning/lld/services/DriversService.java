package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.DriverAlreadyExists;
import org.learning.lld.models.Driver;
import org.learning.lld.repositories.IDriversRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DriversService {
    private final IDriversRepository driversRepository;

    public DriversService(IDriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    public void registerDriver(@NonNull final Driver driver) {
        if (this.driversRepository.getDriver(driver.getId()) != null) {
            throw new DriverAlreadyExists();
        }
        this.driversRepository.registerDriver(driver);
    }

    public List<Driver> getServiceableDrivers() {
        return this.driversRepository.getAllDrivers().stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }
}
