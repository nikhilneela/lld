package org.learning.lld.commands;

import lombok.NonNull;
import org.learning.lld.models.Command;
import org.learning.lld.services.ParkingLotService;

public abstract class CommandExecutor {
    protected final ParkingLotService parkingLotService;
    public abstract void execute(@NonNull final Command command);
    public abstract boolean validate(@NonNull final Command command);

    public CommandExecutor(@NonNull final ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService1;
    }
}
