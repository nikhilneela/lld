package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Trip;

public interface ITripsRepository {
    void addTrip(@NonNull final Trip trip);
}
