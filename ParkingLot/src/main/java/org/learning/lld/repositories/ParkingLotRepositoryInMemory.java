package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepositoryInMemory implements IParkingLotRepository {
    private final List<ParkingLot> parkingLots;

    public ParkingLotRepositoryInMemory() {
        this.parkingLots = new ArrayList<>();
    }

    @Override
    public ParkingLot createParkingLot(@NonNull String id, int numSlots) {
        ParkingLot parkingLot = new ParkingLot(id, numSlots);
        this.parkingLots.add(parkingLot);
        return parkingLot;
    }

    @Override
    public ParkingLot getParkingLot(@NonNull String id) {
        return this.parkingLots.stream().filter(parkingLot -> parkingLot.getId().equals(id)).findFirst().orElse(null);
    }
}
