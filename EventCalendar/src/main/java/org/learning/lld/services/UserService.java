package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.models.*;
import org.learning.lld.repositories.IUserRepository;
import org.learning.lld.repositories.UserInMemoryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {
    private final IUserRepository userRepository;
    //store user -> user events per day
    private final Map<User, Map<LocalDate, TreeSet<UserEvent>>> userEventsMap;

    public UserService() {
        this.userRepository = new UserInMemoryRepository();
        this.userEventsMap = new HashMap<>();
    }

    public User createUser(@NonNull final String userName, @NonNull final LocalTime startTime, @NonNull final LocalTime endTime) {
        //assume we have done validations in the controller layer
        //Generally business specific validations are done in service layer and basic/obvious validations are done in controller layer
        //example, userName cannot contain special characters -> do it in controller layer
        //example, workingHours cannot be more than 8 hours -> do it in service layer
        //example, startTime has to be less than endTime -> do it in controller layer
        User user = new User(UUID.randomUUID().toString(), userName, new WorkingHours(startTime, endTime));
        this.userRepository.createUser(user);
        return user;
    }

    public User getUser(@NonNull final String id) {
        return this.userRepository.getUser(id);
    }

    public boolean isUserAvailable(@NonNull final User user, @NonNull final TimeSlot timeSlot) {
        //check if timeslot falls within user's working hours
        if (!user.isAvailable(timeSlot)) {
            return false;
        }

        Map<LocalDate, TreeSet<UserEvent>> userEventsPerDay = this.userEventsMap.getOrDefault(user, null);
        if (userEventsPerDay == null) {
            return true;
        }

        LocalDate requiredSlotDate = timeSlot.getStartTime().toLocalDate();
        TreeSet<UserEvent> userEvents = userEventsPerDay.getOrDefault(requiredSlotDate, null);

        if (userEvents == null) {
            return true;
        }

        UserEvent userEventToInsert = new UserEvent(null, null, timeSlot);

        if (userEvents.contains(userEventToInsert)) {
            return false;
        }

        UserEvent before = userEvents.lower(userEventToInsert);
        UserEvent after = userEvents.higher(userEventToInsert);

        if (before != null && before.getTimeSlot().getEndTime().isAfter(timeSlot.getStartTime())) {
            return false;
        }

        if (after != null && after.getTimeSlot().getStartTime().isBefore(timeSlot.getEndTime())) {
            return false;
        }
        return true;
    }

    public void blockCalender(@NonNull final User user, @NonNull final Event event, @NonNull final TimeSlot timeSlot) {
        Map<LocalDate, TreeSet<UserEvent>> userEventsPerDay = userEventsMap.getOrDefault(user, new HashMap<>());
        TreeSet<UserEvent> userEvents =  userEventsPerDay.getOrDefault(timeSlot.getStartTime().toLocalDate(), new TreeSet<>());
        userEvents.add(new UserEvent(user, event, timeSlot));
        userEventsPerDay.put(timeSlot.getStartTime().toLocalDate(), userEvents);
        userEventsMap.put(user, userEventsPerDay);
    }

    public List<UserEvent> getEvents(@NonNull final User user, @NonNull final TimeSlot timeSlot) {
        //request is for the same day
        if (timeSlot.getStartTime().toLocalDate() == timeSlot.getEndTime().toLocalDate()) {
            return getEvents(user, timeSlot.getStartTime().toLocalDate(), timeSlot.getStartTime().toLocalTime(), timeSlot.getEndTime().toLocalTime());
        }

        //request spans over multiple days
        //grab events for the first day
        List<UserEvent> events = new ArrayList<>(getEvents(user, timeSlot.getStartTime().toLocalDate(), timeSlot.getStartTime().toLocalTime(), null));

        //grab events for rest of the days
        LocalDate startDate = timeSlot.getStartTime().toLocalDate().plusDays(1);
        LocalDate endDate = timeSlot.getEndTime().toLocalDate().minusDays(1);
        while (!startDate.isAfter(endDate)) {
            events.addAll(getEvents(user, startDate, null, null));
            startDate = startDate.plusDays(1);
        }

        //grab events for the last day
        events.addAll(getEvents(user, timeSlot.getEndTime().toLocalDate(), null, timeSlot.getEndTime().toLocalTime()));
        return events;
    }

    private List<UserEvent> getEvents(@NonNull final User user, @NonNull final LocalDate date, final LocalTime start, final LocalTime end) {
        Map<LocalDate, TreeSet<UserEvent>> userEventsPerDay = userEventsMap.getOrDefault(user, new HashMap<>());
        TreeSet<UserEvent> userEvents =  userEventsPerDay.getOrDefault(date, new TreeSet<>());
        return userEvents.stream().filter(userEvent -> (start == null || !userEvent.getTimeSlot().getStartTime().toLocalTime().isBefore(start)) && (end == null || userEvent.getTimeSlot().getStartTime().toLocalTime().isBefore(end))).collect(Collectors.toList());
    }
}
