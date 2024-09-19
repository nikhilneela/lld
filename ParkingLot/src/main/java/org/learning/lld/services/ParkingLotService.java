package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoMatchingSlotsAvailableException;
import org.learning.lld.exceptions.NoSlotsAvailableException;
import org.learning.lld.exceptions.NoSuchVehicleFoundException;
import org.learning.lld.models.Color;
import org.learning.lld.models.IVehicle;
import org.learning.lld.models.ParkingLot;
import org.learning.lld.models.Slot;
import org.learning.lld.repositories.IParkingLotRepository;
import org.learning.lld.strategies.ISlotPickingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private final IParkingLotRepository parkingLotRepository;
    private final ISlotPickingStrategy slotPickingStrategy;

    public ParkingLotService(@NonNull final IParkingLotRepository parkingLotRepository, @NonNull final ISlotPickingStrategy slotPickingStrategy) {
        this.parkingLotRepository = parkingLotRepository;
        this.slotPickingStrategy = slotPickingStrategy;
    }

    public void createParkingLot(@NonNull final String id, int numSlots) {
        if (this.parkingLot != null) {
            this.parkingLot = new ParkingLot(id, numSlots);
        }
    }

    public Slot park(IVehicle vehicle) {
        List<Slot> availableSlots = parkingLot.getAvailableSlots();

        if (availableSlots == null) {
            throw new NoSlotsAvailableException();
        }

        Slot selectedSlot = this.slotPickingStrategy.pickSlot(availableSlots);

        if (selectedSlot == null) {
            throw new NoMatchingSlotsAvailableException();
        }

        selectedSlot.assignVehicle(vehicle);
        return selectedSlot;
    }

    public void vacate(IVehicle vehicle) {
        Slot slot = this.parkingLot.getSlot(vehicle);
        if (slot == null) {
            throw new NoSuchVehicleFoundException();
        }
        slot.vacate();
    }

    public List<Slot> getSlotsForVehicles(List<IVehicle> vehicles) {
        return vehicles.stream().map(this.parkingLot::getSlot).collect(Collectors.toList());
    }

    public List<Slot> getSlotsForVehiclesWithColor(ParkingLot parkingLot, Color color) {
        return parkingLot.getSlotsWithVehicleColor(color);
    }
}
