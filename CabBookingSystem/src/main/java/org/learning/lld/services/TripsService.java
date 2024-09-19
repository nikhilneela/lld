package org.learning.lld.services;

import org.learning.lld.models.Driver;
import org.learning.lld.models.Location;
import org.learning.lld.models.Rider;
import org.learning.lld.models.Trip;
import org.learning.lld.repositories.ITripsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TripsService {
    private final ITripsRepository tripsRepository;
    private final DriversService driversService;

    public TripsService(ITripsRepository repository, DriversService driversService) {
        this.tripsRepository = repository;
        this.driversService = driversService;
    }

    public Trip createTrip(Rider rider, Location fromLocation, Location toLocation) {
        //get all serviceable drivers
        //filter out drivers that are already in trips
        //filter out drivers that are within the max distance
        //sort the drivers with distance from riders location
        //pick the first one

        List<Driver> serviceableDrivers = driversService.getServiceableDrivers();
        List<Driver> availableDrivers = serviceableDrivers.stream().filter(driver -> driver.getCurrentTrip() == null).toList();
    }
}
