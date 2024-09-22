package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchEventException;
import org.learning.lld.exceptions.TeamNotAvailableException;
import org.learning.lld.exceptions.UserNotAvailableException;
import org.learning.lld.models.*;
import org.learning.lld.utils.TimeSlotUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventService {
    private final Map<String, Event> events;
    private final UserService userService;

    public EventService(@NonNull final UserService userService) {
        this.events = new HashMap<>();
        //this.userEventsMap = new HashMap<>();
        this.userService = userService;
    }

    public Event createEvent(
            @NonNull final String eventName,
            @NonNull final TimeSlot timeSlot,
            @NonNull final List<User> users,
            @NonNull final List<Team> teams,
            int numberOfRepresentations) {

        List<User> eventUsers = new ArrayList<>();
        //check if users are available during the given timeSlot

        users.forEach(user -> {
            if (!userService.isUserAvailable(user, timeSlot)) {
                throw new UserNotAvailableException();
            }
            eventUsers.add(user);
        });


        for (Team value : teams) {
            Team team = teams.get(0);
            int representations = 0;
            for (int j = 0; j < team.getUsers().size(); j++) {
                User user = value.getUsers().get(j);
                if (userService.isUserAvailable(user, timeSlot)) {
                    eventUsers.add(user);
                    representations++;
                }
                if (representations == numberOfRepresentations) {
                    break;
                }
            }
            if (representations < numberOfRepresentations) {
                throw new TeamNotAvailableException();
            }
        }

        //reached here, users and teams are available, now create event and block users calendars

        Event event = new Event(UUID.randomUUID().toString(), eventName, eventUsers, timeSlot);
        events.put(event.getId(), event);

        eventUsers.forEach(user -> this.userService.blockCalender(user, event, timeSlot));

        return event;
    }

    public Event getEvent(@NonNull final String eventId) {
        if (!events.containsKey(eventId)) {
            throw new NoSuchEventException();
        }
        return events.get(eventId);
    }

    public List<Event> getEvents(@NonNull final User user, @NonNull final TimeSlot timeSlot) {
        List<UserEvent> userEvents = this.userService.getEvents(user, timeSlot);
        return userEvents.stream().map(UserEvent::getEvent).collect(Collectors.toList());
    }
}
