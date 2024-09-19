package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Driver;

import java.util.List;

public interface IDriversRepository {
    void registerDriver(@NonNull final Driver driver);
    Driver getDriver(@NonNull final String id);
    List<Driver> getAllDrivers();
}
