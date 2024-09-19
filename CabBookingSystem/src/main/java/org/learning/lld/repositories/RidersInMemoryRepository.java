package org.learning.lld.repositories;

import org.learning.lld.exceptions.RiderAlreadyExists;
import org.learning.lld.models.Rider;

import java.util.ArrayList;
import java.util.List;

public class RidersInMemoryRepository implements IRidersRepository {
    private final List<Rider> riders;

    public RidersInMemoryRepository() {
        this.riders = new ArrayList<>();
    }

    @Override
    public void registerRider(Rider rider) {
        if (this.riders.stream().anyMatch(r -> r.getId().equals(rider.getId()))) {
            throw new RiderAlreadyExists();
        }
        this.riders.add(rider);
    }
}
