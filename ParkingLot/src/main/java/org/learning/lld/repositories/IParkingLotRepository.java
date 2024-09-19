package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.ParkingLot;

import java.lang.annotation.Native;

public interface IParkingLotRepository {
    ParkingLot createParkingLot(@NonNull final String id, int numSlots);
    ParkingLot getParkingLot(@NonNull final String id);
}
