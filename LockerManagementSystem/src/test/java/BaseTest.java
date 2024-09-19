import org.junit.Before;
import org.learning.lld.controllers.LockerController;
import org.learning.lld.controllers.OrderController;
import org.learning.lld.repositories.ISlotOtpRepository;
import org.learning.lld.repositories.LockerRepository;
import org.learning.lld.repositories.SlotOtpRepository;
import org.learning.lld.services.LockerService;
import org.learning.lld.services.NotificationService;
import org.learning.lld.services.OtpService;
import org.learning.lld.strategies.*;
import org.mockito.Mock;

public class BaseTest {
    protected LockerController lockerController;
    protected OrderController orderController;
    protected LockerService lockerService;
    protected OtpService otpService;
    @Mock
    protected NotificationService notificationService;

    @Before
    public void setup() {
        LockerRepository lockerRepository = new LockerRepository();
        ISlotFilteringStrategy slotFilteringStrategy = new MatchingSizeSlotFilteringStrategy();
        IRandomNumberGenerator randomNumberGenerator = new SimpleRandomNumberGenerator();
        ISlotAssignmentStrategy slotAssignmentStrategy = new RandomSlotAssignmentStrategy(randomNumberGenerator);
        this.lockerService = new LockerService(lockerRepository, slotFilteringStrategy, slotAssignmentStrategy);
        IOtpGenerator otpGenerator = new OtpGeneratorRandom(5, randomNumberGenerator);
        ISlotOtpRepository slotOtpRepository = new SlotOtpRepository();
        this.otpService = new OtpService(otpGenerator, slotOtpRepository);
        this.lockerController = new LockerController(this.lockerService, this.otpService);
        this.orderController = new OrderController(this.lockerService, this.notificationService, this.otpService);
    }
}
