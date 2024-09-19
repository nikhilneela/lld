package scenarios;

import lombok.NonNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.learning.lld.controllers.*;
import org.learning.lld.models.User;
import org.learning.lld.providers.ISeatLockProvider;
import org.learning.lld.providers.InMemorySeatLockProvider;
import org.learning.lld.services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTest {
    protected BookingController bookingController;
    protected MovieController movieController;
    protected PaymentController paymentController;
    protected ShowController showController;
    protected TheatreController theatreController;
    protected UserController userController;
    private BookingService bookingService;
    private MovieService movieService;
    private PaymentService paymentService;
    private ShowAvailabilityService showAvailabilityService;
    private TheatreService theatreService;
    private UserService userService;
    private ShowService showService;
    private ISeatLockProvider seatLockProvider;
    private final Integer LOCK_TIMEOUT_IN_SECONDS = 15 * 60;

    protected String u1, u2;
    protected String m1, m2, m3, m4, m5, m6, m7;
    protected String t1, t2, t3, t4, t5;
    protected String t1s1, t1s2, t1s3, t2s1, t2s2, t3s1, t3s2, t4s1, t4s2, t5s1;
    protected String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

    @Before
    public void setup() {
        setupControllers();
        //setupResources();
    }

    public void setupControllers() {
        this.seatLockProvider = new InMemorySeatLockProvider(LOCK_TIMEOUT_IN_SECONDS);
        this.bookingService = new BookingService(this.seatLockProvider);
        this.showService = new ShowService();
        this.theatreService = new TheatreService();
        this.userService = new UserService();
        this.movieService = new MovieService();
        this.paymentService = new PaymentService(this.seatLockProvider);
        this.showAvailabilityService = new ShowAvailabilityService(this.bookingService, this.seatLockProvider);
        this.bookingController = new BookingController(this.bookingService, this.showService, this.theatreService, this.userService);
        this.movieController = new MovieController(this.movieService);
        this.paymentController = new PaymentController(this.paymentService, this.userService, this.bookingService);
        this.showController = new ShowController(this.showService, this.theatreService, this.movieService, this.showAvailabilityService);
        this.theatreController = new TheatreController(this.theatreService);
        this.userController = new UserController(this.userService);
    }

    public void setupResources() {

        //create theatres
        t1 = this.theatreController.createTheatre("pvr gachibowli");
        t2 = this.theatreController.createTheatre("aparna neo cinemas");
        t3 = this.theatreController.createTheatre("jp cinemas");
        t4 = this.theatreController.createTheatre("imax");
        t5 = this.theatreController.createTheatre("sudarshan 70mm");

        //create screens
        t1s1 = this.theatreController.addScreen(t1, "AUDI 1");
        t1s2 = this.theatreController.addScreen(t1, "AUDI 2");
        t1s3 = this.theatreController.addScreen(t1, "AUDI 3");

        t2s1 = this.theatreController.addScreen(t2, "AUDI 1");
        t2s2 = this.theatreController.addScreen(t2, "AUDI 2");

        t3s1 = this.theatreController.addScreen(t3, "AUDI 1");
        t3s2 = this.theatreController.addScreen(t3, "AUDI 2");

        t4s1 = this.theatreController.addScreen(t4, "AUDI 1");
        t4s2 = this.theatreController.addScreen(t4, "AUDI 2");

        t5s1 = this.theatreController.addScreen(t5, "AUDI 1");

        //create seats
        //create seats for t1s1
        this.theatreController.addSeatToScreen(t1s1, 1, 1);
        this.theatreController.addSeatToScreen(t1s1, 1, 2);
        this.theatreController.addSeatToScreen(t1s1, 1, 3);
        this.theatreController.addSeatToScreen(t1s1, 1, 4);
        this.theatreController.addSeatToScreen(t1s1, 1, 5);
        //create seats for t1s2
        this.theatreController.addSeatToScreen(t1s2, 1, 1);
        this.theatreController.addSeatToScreen(t1s2, 1, 2);
        this.theatreController.addSeatToScreen(t1s2, 1, 3);
        this.theatreController.addSeatToScreen(t1s2, 1, 4);
        this.theatreController.addSeatToScreen(t1s2, 1, 5);
        //create seats for t1s3
        this.theatreController.addSeatToScreen(t1s3, 1, 1);
        this.theatreController.addSeatToScreen(t1s3, 1, 2);
        this.theatreController.addSeatToScreen(t1s3, 1, 3);
        this.theatreController.addSeatToScreen(t1s3, 1, 4);
        this.theatreController.addSeatToScreen(t1s3, 1, 5);
        //create seats for t2s1
        this.theatreController.addSeatToScreen(t2s1, 1, 1);
        this.theatreController.addSeatToScreen(t2s1, 1, 2);
        this.theatreController.addSeatToScreen(t2s1, 1, 3);
        this.theatreController.addSeatToScreen(t2s1, 1, 4);
        this.theatreController.addSeatToScreen(t2s1, 1, 5);
        //create seats for t2s2
        this.theatreController.addSeatToScreen(t2s2, 1, 1);
        this.theatreController.addSeatToScreen(t2s2, 1, 2);
        this.theatreController.addSeatToScreen(t2s2, 1, 3);
        this.theatreController.addSeatToScreen(t2s2, 1, 4);
        this.theatreController.addSeatToScreen(t2s2, 1, 5);
        //create seats for t3s1
        this.theatreController.addSeatToScreen(t3s1, 1, 1);
        this.theatreController.addSeatToScreen(t3s1, 1, 2);
        this.theatreController.addSeatToScreen(t3s1, 1, 3);
        //create seats for t3s2
        this.theatreController.addSeatToScreen(t3s2, 1, 1);
        this.theatreController.addSeatToScreen(t3s2, 1, 2);
        this.theatreController.addSeatToScreen(t3s2, 1, 3);
        //create seats for t4s1
        this.theatreController.addSeatToScreen(t4s1, 1, 1);
        this.theatreController.addSeatToScreen(t4s1, 1, 2);
        this.theatreController.addSeatToScreen(t4s1, 1, 3);
        this.theatreController.addSeatToScreen(t4s1, 1, 4);
        this.theatreController.addSeatToScreen(t4s1, 1, 5);
        this.theatreController.addSeatToScreen(t4s1, 1, 6);
        this.theatreController.addSeatToScreen(t4s1, 1, 7);
        //create seats for t4s2
        this.theatreController.addSeatToScreen(t4s2, 1, 1);
        this.theatreController.addSeatToScreen(t4s2, 1, 2);
        this.theatreController.addSeatToScreen(t4s2, 1, 3);
        //create seats for t5s1
        this.theatreController.addSeatToScreen(t5s1, 1, 1);
        this.theatreController.addSeatToScreen(t5s1, 1, 2);
        this.theatreController.addSeatToScreen(t5s1, 1, 3);
        this.theatreController.addSeatToScreen(t5s1, 1, 4);
        this.theatreController.addSeatToScreen(t5s1, 1, 5);
        this.theatreController.addSeatToScreen(t5s1, 2, 1);
        this.theatreController.addSeatToScreen(t5s1, 2, 2);
        this.theatreController.addSeatToScreen(t5s1, 2, 3);
        this.theatreController.addSeatToScreen(t5s1, 2, 4);
        this.theatreController.addSeatToScreen(t5s1, 2, 5);

        //create movies
        m1 = this.movieController.createMovie("kantara");
        m2 = this.movieController.createMovie("kalki");
        m3 = this.movieController.createMovie("premam");
        m4 = this.movieController.createMovie("premam");
        m5 = this.movieController.createMovie("sitaramam");
        m6 = this.movieController.createMovie("saripoda sanivaram");
        m7 = this.movieController.createMovie("ponnian selvan");

        s1 = this.showController.addShow(m1, t1s1, new Date(), 3 * 60 * 60);
        s2 = this.showController.addShow(m1, t1s2, new Date(), 3 * 60 * 60);
        s3 = this.showController.addShow(m2, t1s3, new Date(), 3 * 60 * 60);

        s4 = this.showController.addShow(m3, t2s1, new Date(), 3 * 60 * 60);
        s5 = this.showController.addShow(m3, t2s2, new Date(), 3 * 60 * 60);

        s6 = this.showController.addShow(m4, t3s1, new Date(), 3 * 60 * 60);
        s7 = this.showController.addShow(m4, t3s2, new Date(), 3 * 60 * 60);

        s8 = this.showController.addShow(m5, t4s1, new Date(), 3 * 60 * 60);
        s9 = this.showController.addShow(m6, t4s2, new Date(), 3 * 60 * 60);

        s10 = this.showController.addShow(m7, t5s1, new Date(), 3 * 60 * 60);

        //create users
        u1 = this.userController.createUser("nikhil");
        u2 = this.userController.createUser("chandu");
    }

    public String setupScreen(@NonNull final String theatreName, @NonNull final String screenName) {
        String t1 = this.theatreController.createTheatre(theatreName);
        return this.theatreController.addScreen(t1, screenName);
    }

    public List<String> setupSeats(@NonNull final String screenId, int numRows, int numColumns) {
        List<String> seatIds = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; j++) {
                seatIds.add(this.theatreController.addSeatToScreen(screenId, i + 1, j + 1));
            }
        }
        return seatIds;
    }

    public String setupShow(@NonNull final String movieName, @NonNull final String screenId) {
        String movieId = this.movieController.createMovie(movieName);
        return this.showController.addShow(movieId, screenId, new Date(), 3 * 60 * 60);
    }

    public void validateSeats(@NonNull final List<String> allSeats, @NonNull final List<String> allAvailableSeats) {
        for (String seat : allAvailableSeats) {
            Assert.assertTrue(allSeats.contains(seat));
        }
    }

    protected void validateSeatsAreExcluded(List<String> excludedSeats, List<String> availableSeats) {
        for(String seat : excludedSeats) {
            Assert.assertFalse(availableSeats.contains(seat));
        }
    }
}
