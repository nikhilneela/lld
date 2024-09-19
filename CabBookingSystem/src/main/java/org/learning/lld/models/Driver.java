package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.learning.lld.exceptions.DriverNotAvailable;

public class Driver {
    @Getter
    private final String id;
    private final Cab cab;
    @Getter
    private Trip currentTrip;
    @Getter
    private boolean isAvailable;

    public Driver(String id, Cab cab) {
        this.id = id;
        this.cab = cab;
    }

    public void setAvailable() {
        if (currentTrip != null) {
            throw new DriverNotAvailable();
        }
        this.isAvailable = true;
    }

    public void setCurrentTrip(@NonNull final Trip trip) {
        if (this.currentTrip != null) {
            throw new DriverNotAvailable();
        }
        this.currentTrip = trip;
    }
}
