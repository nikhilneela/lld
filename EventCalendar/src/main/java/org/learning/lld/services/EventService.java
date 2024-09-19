package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchEventException;
import org.learning.lld.exceptions.TeamNotAvailableException;
import org.learning.lld.exceptions.UserNotAvailableException;
import org.learning.lld.models.Event;
import org.learning.lld.models.Team;
import org.learning.lld.models.TimeSlot;
import org.learning.lld.models.User;
import org.learning.lld.utils.TimeSlotUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventService {
    private final Map<String, Event> events;
    private final Map<LocalDate, Map<User, List<TimeSlot>>> userEventsMap;

    public EventService() {
        this.events = new HashMap<>();
        this.userEventsMap = new HashMap<>();
    }

    public Event createEvent(
            @NonNull final String eventName,
            @NonNull final TimeSlot timeSlot,
            @NonNull final List<User> users,
            @NonNull final List<Team> teams,
            int numberOfRepresentations) {
        //our core logic
        //check if timeslot is within user's working hours
        if (isAnyUserNotAvailable(users, timeSlot)) {
            throw new UserNotAvailableException();
        }

        teams.forEach(team -> {
            if (team.getUsers().stream().filter(user -> user.isAvailable(timeSlot)).count() < numberOfRepresentations) {
                throw new TeamNotAvailableException();
            }
        });

        List<User> eventUsers = new ArrayList<>();
        //check if users are available during the given timeSlot
        LocalDate eventDate = timeSlot.getStartTime().toLocalDate();
        Map<User, List<TimeSlot>> userAvailabilities = userEventsMap.getOrDefault(eventDate, new HashMap<>());

        users.forEach(user -> {
            List<TimeSlot> userSlots = userAvailabilities.getOrDefault(user, new ArrayList<>());
            if (!TimeSlotUtils.isSlotAvailable(userSlots, timeSlot)) {
                throw new UserNotAvailableException();
            }
            userSlots.add(timeSlot);
            userAvailabilities.put(user, userSlots);
            eventUsers.add(user);
        });

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(0);
            int representations = 0;
            for (int j = 0; j < team.getUsers().size(); j++) {
                User user = teams.get(i).getUsers().get(j);
                List<TimeSlot> userSlots = userAvailabilities.getOrDefault(user, new ArrayList<>());
                if (TimeSlotUtils.isSlotAvailable(userSlots, timeSlot)) {
                    userSlots.add(timeSlot);
                    userAvailabilities.put(user, userSlots);
                    eventUsers.add(user);
                    representations++;
                    if (representations == numberOfRepresentations) {
                        break;
                    }
                }
            }
            if (representations < numberOfRepresentations) {
                throw new TeamNotAvailableException();
            }
        }
        userEventsMap.put(eventDate, userAvailabilities);

        Event event = new Event(UUID.randomUUID().toString(), eventName, eventUsers, timeSlot);
        events.put(event.getId(), event);
        return event;
    }

    public Event getEvent(@NonNull final String eventId) {
        if (!events.containsKey(eventId)) {
            throw new NoSuchEventException();
        }
        return events.get(eventId);
    }

    private boolean isAnyUserNotAvailable(@NonNull final List<User> users, @NonNull final TimeSlot timeSlot) {
        return users.stream().anyMatch(user -> !user.isAvailable(timeSlot));
    }
}
