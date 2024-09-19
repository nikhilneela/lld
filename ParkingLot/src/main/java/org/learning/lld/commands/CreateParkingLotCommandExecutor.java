package org.learning.lld.commands;

import lombok.NonNull;
import org.learning.lld.models.Command;
import org.learning.lld.services.ParkingLotService;

import java.util.UUID;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
    public CreateParkingLotCommandExecutor(@NonNull ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public void execute(@NonNull Command command) {
        int numberOfSlots = Integer.parseInt(command.getArguments().get(0));
        this.parkingLotService.createParkingLot(UUID.randomUUID().toString(), numberOfSlots);
    }

    @Override
    public boolean validate(@NonNull Command command) {
        //there should be exactly one argument and it should be integer
        //perform these checks and return true/false
        return true;
    }
}
