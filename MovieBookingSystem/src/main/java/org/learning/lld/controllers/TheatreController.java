package org.learning.lld.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.models.Screen;
import org.learning.lld.models.Theatre;
import org.learning.lld.services.TheatreService;

@AllArgsConstructor
public class TheatreController {
    private TheatreService theatreService;

    public String createTheatre(@NonNull final String name) {
        //TODO : name validation checks
        return theatreService.createTheatre(name).getId();
    }

    public String addScreen(@NonNull final String theatreId, @NonNull final String screenName) {
        Theatre theatre = this.theatreService.getTheatre(theatreId);
        return this.theatreService.addScreenToTheatre(theatre, screenName).getId();
    }

    public String addSeatToScreen(@NonNull final String screenId, @NonNull final Integer rowNo, @NonNull final Integer colNo) {
        Screen screen = theatreService.getScreen(screenId);
        return theatreService.addSeatToScreen(screen, rowNo, colNo).getId();
    }
}
