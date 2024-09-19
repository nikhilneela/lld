package org.learning.lld.commands;

import lombok.NonNull;
import org.learning.lld.models.*;
import org.learning.lld.services.ParkingLotService;

import java.util.Arrays;
import java.util.List;

public class ParkCommandExecutor extends CommandExecutor {
    public ParkCommandExecutor(@NonNull ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public void execute(@NonNull Command command) {
        List<String> args = command.getArguments();
        RegistrationNumber registrationNumber = new RegistrationNumber(args.get(0));
        Color color = Color.valueOf(args.get(1));
        IVehicle vehicle = new Car(registrationNumber, color);
        Slot slot = this.parkingLotService.park(vehicle);
    }

    @Override
    public boolean validate(@NonNull Command command) {
        List<String> args = command.getArguments();
        if (args.size() != 2) {
            return false;
        }
        return Arrays.stream(Color.values()).anyMatch(color -> color.name().equals(args.get(1)));
    }
}
