package org.learning.lld.commands;

import lombok.NonNull;
import org.learning.lld.models.Command;
import org.learning.lld.services.ParkingLotService;

public class CommandExecutorFactory {
    private static final String PARK_COMMAND = "park";
    private static final String LEAVE_COMMAND = "park";
    private static final String REGISTRATION_NUMBERS_WITH_COLOR_COMMAND = "registration_numbers_for_cars_with_colour";
    private static final String SLOT_NUMBERS_FOR_CARS_WITH_COLORS_COMMAND = "slot_numbers_for_cars_with_colour";
    private static final String SLOT_NUMBERS_FOR_REGISTRATION_NUMBERS_COMMAND = "slot_number_for_registration_number";
    private static final String EXIT_COMMAND = "exit";
    private static final String STATUS_COMMAND = "status";
    private static final String CREATE_PARKING_LOT_COMMAND = "create_parking_lot";

    private final ParkingLotService parkingLotService;

    public CommandExecutorFactory(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public CommandExecutor getCommandExecutor(@NonNull final Command command) {
        if (command.getCommandName().equals(CREATE_PARKING_LOT_COMMAND)) {
            return new CreateParkingLotCommandExecutor(parkingLotService);
        }
    }
}
