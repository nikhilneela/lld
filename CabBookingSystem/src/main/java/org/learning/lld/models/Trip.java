package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Trip {
    private final Location start;
    private final Location end;
    @Getter
    private final String id;
    private final Rider rider;
    private final Driver driver;
    private final Fare fare;
    @Setter
    private Location currentLocation;
    private TripState state;

    public Trip(@NonNull final Location start, @NonNull final Location end, @NonNull final String id, @NonNull final Rider rider, @NonNull final Driver driver, @NonNull final Fare fare) {
        this.start = start;
        this.end = end;
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.fare = fare;
        this.state = TripState.CREATED;
    }

    public void completeTrip() {
        this.state = TripState.COMPLETED;
    }
}
