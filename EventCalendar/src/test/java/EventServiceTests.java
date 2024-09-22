import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.learning.lld.controllers.EventController;
import org.learning.lld.controllers.TeamController;
import org.learning.lld.controllers.UserController;
import org.learning.lld.exceptions.UserNotAvailableException;
import org.learning.lld.models.Event;
import org.learning.lld.models.TimeSlot;
import org.learning.lld.models.User;
import org.learning.lld.repositories.ITeamRepository;
import org.learning.lld.repositories.IUserRepository;
import org.learning.lld.repositories.TeamInMemoryRepository;
import org.learning.lld.repositories.UserInMemoryRepository;
import org.learning.lld.services.EventService;
import org.learning.lld.services.TeamService;
import org.learning.lld.services.UserService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EventServiceTests {
    private UserController userController;
    private TeamController teamController;
    private EventController eventController;
    private UserService userService;
    private TeamService teamService;
    private EventService eventService;
    private IUserRepository userRepository;
    private ITeamRepository teamRepository;
    private String userA, userB, userC, userD, userE, userF;
    private List<String> userAEventIds;
    private DateTimeFormatter dateTimeFormatter;
    private DateTimeFormatter timeFormatter;
    private String team1, team2;

    @Before
    public void setup() {
        this.userRepository = new UserInMemoryRepository();
        this.userService = new UserService();
        this.userController = new UserController(this.userService);
        this.teamRepository = new TeamInMemoryRepository();
        this.teamService = new TeamService(this.teamRepository);
        this.teamController = new TeamController(this.teamService, this.userService);
        this.eventService = new EventService(this.userService);
        this.eventController = new EventController(this.eventService, this.teamService, this.userService);
        this.userAEventIds = new ArrayList<>();

        this.timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        this.userA = this.userController.createUser("A", LocalTime.parse("10:00 AM", timeFormatter), LocalTime.parse("07:00 PM", timeFormatter));
        this.userB = this.userController.createUser("B", LocalTime.parse("09:30 AM", timeFormatter), LocalTime.parse("05:30 PM", timeFormatter));
        this.userC = this.userController.createUser("C", LocalTime.parse("11:30 AM", timeFormatter), LocalTime.parse("06:30 PM", timeFormatter));
        this.userD = this.userController.createUser("D", LocalTime.parse("10:00 AM", timeFormatter), LocalTime.parse("06:00 PM", timeFormatter));
        this.userE = this.userController.createUser("E", LocalTime.parse("11:00 AM", timeFormatter), LocalTime.parse("07:30 PM", timeFormatter));
        this.userF = this.userController.createUser("F", LocalTime.parse("11:00 AM", timeFormatter), LocalTime.parse("06:30 PM", timeFormatter));

        this.team1 = this.teamController.createTeam("Team1", Arrays.asList(userC, userE));
        this.team2 = this.teamController.createTeam("Team2", Arrays.asList(userB, userD, userF));
    }


    @Test
    public void createEventWithNoPriorEvent() {
        testCase1();
    }

    @Test
    public void testCase2() {
        testCase1();
        String eventName = "event2";

        Assertions.assertThrows(UserNotAvailableException.class, () -> {
            String event2Id = this.eventController.createEvent(
                    eventName,
                    TimeSlot.of(LocalDateTime.parse("20-09-2024 02:00 PM", dateTimeFormatter), LocalDateTime.parse("20-09-2024 03:00 PM", dateTimeFormatter)),
                    Collections.singletonList(userC),
                    new ArrayList<>(),
                    0);
        });
    }

    @Test
    public void testCase3() {
        _testCase3();
    }

    @Test
    public void testCase4() {
        _testCase4();
    }

    @Test
    public void testCase5() {
        _testCase5();
    }

    @Test
    public void testCase6() {
        _testCase5();

        List<Event> events = this.eventController.getEvents(
                userA,
                TimeSlot.of(LocalDateTime.parse("19-09-2024 10:00 AM", dateTimeFormatter), LocalDateTime.parse("20-09-2024 05:00 PM", dateTimeFormatter))
        );

        Assertions.assertEquals(2, events.size());
        List<String> eventNames = events.stream().map(Event::getName).toList();
        Assertions.assertTrue(eventNames.contains("event1"));
        Assertions.assertTrue(eventNames.contains("event4"));
    }

    @Test
    public void testCase7() {
        _testCase5();

        List<Event> events = this.eventController.getEvents(
                userB,
                TimeSlot.of(LocalDateTime.parse("19-09-2024 10:00 AM", dateTimeFormatter), LocalDateTime.parse("20-09-2024 05:00 PM", dateTimeFormatter))
        );

        Assertions.assertEquals(1, events.size());
        List<String> eventNames = events.stream().map(Event::getName).toList();
        Assertions.assertTrue(eventNames.contains("event3") || eventNames.contains("event4"));
    }

    private void testCase1() {
        String eventName = "event1";
        String event1Id = this.eventController.createEvent(
                eventName,
                TimeSlot.of(LocalDateTime.parse("20-09-2024 02:00 PM", dateTimeFormatter), LocalDateTime.parse("20-09-2024 03:00 PM", dateTimeFormatter)),
                Collections.singletonList(userA),
                Collections.singletonList(team1),
                2);
        this.userAEventIds.add(event1Id);
        Event event = this.eventController.getEvent(event1Id);
        Assertions.assertEquals(eventName, event.getName());
        Assertions.assertEquals(3, event.getParticipants().size());
    }

    private void _testCase3() {
        testCase1();
        String eventName = "event3";

        String event2Id = this.eventController.createEvent(
                eventName,
                TimeSlot.of(LocalDateTime.parse("19-09-2024 03:00 PM", dateTimeFormatter), LocalDateTime.parse("19-09-2024 04:00 PM", dateTimeFormatter)),
                new ArrayList<>(),
                Arrays.asList(team1, team2),
                2);

        Event event = this.eventController.getEvent(event2Id);
        Assertions.assertEquals(eventName, event.getName());
        Assertions.assertEquals(4, event.getParticipants().size());
    }

    private void _testCase4() {
        _testCase3();
        String eventName = "event4";
        String event4Id = this.eventController.createEvent(
                eventName,
                TimeSlot.of(LocalDateTime.parse("19-09-2024 03:00 PM", dateTimeFormatter), LocalDateTime.parse("19-09-2024 04:00 PM", dateTimeFormatter)),
                Collections.singletonList(userA),
                Collections.singletonList(team2),
                1);

        Event event = this.eventController.getEvent(event4Id);
        Assertions.assertEquals(eventName, event.getName());
        Assertions.assertEquals(2, event.getParticipants().size());
    }

    private void _testCase5() {
        _testCase4();
        String eventName = "event5";
        Assertions.assertThrows(UserNotAvailableException.class, () -> {
            String event5Id = this.eventController.createEvent(
                    eventName,
                    TimeSlot.of(LocalDateTime.parse("19-09-2024 10:00 AM", dateTimeFormatter), LocalDateTime.parse("19-09-2024 11:00 AM", dateTimeFormatter)),
                    Arrays.asList(userA, userF),
                    new ArrayList<>(),
                    0);
        });
    }
}
