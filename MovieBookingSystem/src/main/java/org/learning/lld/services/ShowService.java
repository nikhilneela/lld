package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchShowException;
import org.learning.lld.exceptions.ScreenAlreadyOccupiedException;
import org.learning.lld.models.Movie;
import org.learning.lld.models.Screen;
import org.learning.lld.models.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show createShow(@NonNull final Screen screen, @NonNull final Movie movie, @NonNull final Date date, @NonNull final Integer durationInSeconds) {

        if (!checkIfShowCreationAllowed(screen, date, durationInSeconds)) {
            throw new ScreenAlreadyOccupiedException();
        }

        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, movie, date, durationInSeconds, screen);
        this.shows.put(showId, show);
        return show;
    }

    public Show getShow(@NonNull final String id) {
        if (!this.shows.containsKey(id)) {
            throw new NoSuchShowException();
        }
        return this.shows.get(id);
    }

    private boolean checkIfShowCreationAllowed(@NonNull final Screen screen, @NonNull final Date date, @NonNull final Integer durationInSeconds) {
        //TODO implement this
        return true;
    }
}
