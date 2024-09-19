import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.learning.lld.models.*;
import org.learning.lld.models.Package;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import utils.LockerUtils;
import utils.RandomUtils;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OtpVerificationTests extends BaseTest {
    @Test
    public void testOtpVerificationWorksCorrectly() {
        LockerUtils.createLockerWithSlots(this.lockerController,
                Arrays.asList(
                        new Size(12, 12),
                        new Size(12, 12),
                        new Size(12, 12),
                        new Size(12, 12)
                )
        );

        LockerItem lockerItem = new Package(new Size(10, 10), RandomUtils.randomString());
        Buyer buyer = RandomUtils.randomBuyer();
        Slot slot = this.orderController.allocateLocker(buyer, lockerItem);

        ArgumentCaptor<String> otpCaptor = ArgumentCaptor.forClass(String.class);
        verify(notificationService).notify(eq(buyer), otpCaptor.capture(), eq(slot));
        final String otp = otpCaptor.getValue();

        Assertions.assertTrue(this.lockerController.unlockSlot(otp, slot));
    }
}
