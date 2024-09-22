package org.learning.lld.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.exceptions.BadRequestException;
import org.learning.lld.models.Event;
import org.learning.lld.models.Team;
import org.learning.lld.models.TimeSlot;
import org.learning.lld.models.User;
import org.learning.lld.services.EventService;
import org.learning.lld.services.TeamService;
import org.learning.lld.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final TeamService teamService;
    private final UserService userService;

    public String createEvent(@NonNull final String eventName, @NonNull final TimeSlot timeSlot, @NonNull final List<String> userIds, @NonNull final List<String> teamIds, int numberOfRepresentations) {
        List<User> users = userIds.stream().map(this.userService::getUser).toList();
        List<Team> teams = teamIds.stream().map(this.teamService::getTeam).toList();

        //ensure each team has at least the required representatives
        teams.forEach(team -> {
            if (team.getUsers().size() < numberOfRepresentations) {
                throw new BadRequestException();
            }
        });

        return this.eventService.createEvent(eventName, timeSlot, users, teams, numberOfRepresentations).getId();
    }

    public Event getEvent(@NonNull final String eventId) {
        return this.eventService.getEvent(eventId);
    }

    public List<Event> getEvents(@NonNull final String userId, @NonNull final TimeSlot timeSlot) {
        User user = this.userService.getUser(userId);
        return this.eventService.getEvents(user, timeSlot);
    }
}
