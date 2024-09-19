package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.exceptions.RiderAlreadyExists;
import org.learning.lld.exceptions.TripAlreadyExists;
import org.learning.lld.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripsInMemoryRepository implements ITripsRepository {
    private final List<Trip> trips;

    public TripsInMemoryRepository() {
        this.trips = new ArrayList<>();
    }

    @Override
    public void addTrip(@NonNull Trip trip) {
        if (this.trips.stream().anyMatch(t -> t.getId().equals(trip.getId()))) {
            throw new TripAlreadyExists();
        }
        this.trips.add(trip);
    }
}
