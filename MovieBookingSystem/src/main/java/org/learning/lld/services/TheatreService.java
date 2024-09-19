package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchScreenException;
import org.learning.lld.exceptions.NoSuchSeatException;
import org.learning.lld.exceptions.NoSuchTheatreException;
import org.learning.lld.models.Screen;
import org.learning.lld.models.Seat;
import org.learning.lld.models.Theatre;

import javax.lang.model.util.SimpleElementVisitor14;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TheatreService {
    //I would normally have kept only theatres Map and retreive screens from theatre object
    //But this is good way of doing as in real production system, you won't get the entire theatre object to retrieve
    //screen info. You would typically make a DB query with theatreId and screenId
    //To mimic this behavior, we are storing screens in the map
    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Theatre createTheatre(@NonNull final String name) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, name);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen addScreenToTheatre(@NonNull final Theatre theatre, @NonNull final String screenName) {
        Screen screen = createScreen(screenName);
        theatre.addScreen(screen);
        return screen;
    }

    public Theatre getTheatre(@NonNull final String id) {
        if (!this.theatres.containsKey(id)) {
            throw new NoSuchTheatreException();
        }
        return this.theatres.get(id);
    }

    public Screen getScreen(@NonNull final String screenId) {
        if (!screens.containsKey(screenId)) {
            throw new NoSuchScreenException();
        }
        return screens.get(screenId);
    }

    public Seat addSeatToScreen(@NonNull final Screen screen, @NonNull final Integer rowNo, @NonNull final Integer colNo) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, colNo);
        screen.addSeat(seat);
        seats.put(seatId, seat);
        return seat;
    }

    public List<Seat> getSeats(@NonNull final List<String> seatIds) {
        return seatIds.stream().map(this::getSeat).collect(Collectors.toList());
    }

    private Seat getSeat(@NonNull final String seatId) {
        if (!this.seats.containsKey(seatId)) {
            throw new NoSuchSeatException();
        }
        return this.seats.get(seatId);
    }


    private Screen createScreen(@NonNull final String screenName) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName);
        screens.put(screenId, screen);
        return screen;
    }
}
