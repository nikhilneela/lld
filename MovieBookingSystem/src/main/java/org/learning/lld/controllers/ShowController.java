package org.learning.lld.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.models.Movie;
import org.learning.lld.models.Screen;
import org.learning.lld.models.Seat;
import org.learning.lld.models.Show;
import org.learning.lld.services.MovieService;
import org.learning.lld.services.ShowAvailabilityService;
import org.learning.lld.services.ShowService;
import org.learning.lld.services.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ShowAvailabilityService showAvailabilityService;

    public String addShow(String movieId, String screenId, Date startTime, int durationInSeconds) {
        //TODO add validation checks for startTime and durationInSeconds
        Screen screen = this.theatreService.getScreen(screenId);
        Movie movie = this.movieService.getMovie(movieId);
        return this.showService.createShow(screen, movie, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(@NonNull final String showId) {
        Show show = this.showService.getShow(showId);
        List<Seat> seats = this.showAvailabilityService.getAvailableSeats(show);
        return seats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
