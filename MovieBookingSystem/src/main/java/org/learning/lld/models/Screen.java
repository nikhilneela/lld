package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;
import org.learning.lld.exceptions.SeatAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    @Getter
    private String id;
    private String name;
    private List<Seat> seats;
    private Theatre theatre;

    public Screen(@NonNull final String id, final String name) {
        this.id = id;
        this.name = name;
        this.seats = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        if (this.seats.stream().anyMatch(s -> s.getId().equals(seat.getId()))) {
            throw new SeatAlreadyExistsException();
        }
        this.seats.add(seat);
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }
}
