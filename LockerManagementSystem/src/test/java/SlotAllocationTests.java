import org.junit.Test;
import org.learning.lld.exceptions.NoSlotsAvailableException;
import org.learning.lld.models.*;
import org.learning.lld.models.Package;
import utils.LockerUtils;
import utils.RandomUtils;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;


public class SlotAllocationTests extends BaseTest {
    
    @Test
    public void testSlotAllocation() {

        //Arrange
        LockerUtils.createLockerWithSlots(this.lockerController,
                Arrays.asList(
                        new Size(12, 12),
                        new Size(12, 12),
                        new Size(12, 12),
                        new Size(12, 12)
                )
        );
        LockerItem item1 = new Package(new Size(3,3), RandomUtils.randomString());
        LockerItem item2 = new Package(new Size(12,8), RandomUtils.randomString());
        Buyer randomBuyer = RandomUtils.randomBuyer();

        //Act
        final List<Slot> allAvailableSlots = this.lockerController.getAvailableSlots();

        final Slot slot1 = this.orderController.allocateLocker(randomBuyer, item1);
        final List<Slot> allAvailableSlotsPostAllocation1 = this.lockerController.getAvailableSlots();

        final Slot slot2 = this.orderController.allocateLocker(randomBuyer, item2);
        final List<Slot> allAvailableSlotsPostAllocation2 = this.lockerController.getAvailableSlots();

        //Assert
        //slot1 should be available initially
        Assertions.assertTrue(allAvailableSlots.contains(slot1));
        //after allocation slot1 should not be available
        Assertions.assertFalse(allAvailableSlotsPostAllocation1.contains(slot1));

        //slot2 should be available before item2 has been allocated
        Assertions.assertTrue(allAvailableSlots.contains(slot2));
        Assertions.assertTrue(allAvailableSlotsPostAllocation1.contains(slot2));
        Assertions.assertFalse(allAvailableSlotsPostAllocation2.contains(slot2));

        lockerController.deAllocateSlot(slot1);
        Assertions.assertTrue(lockerController.getAvailableSlots().contains(slot1));
        Assertions.assertFalse(lockerController.getAvailableSlots().contains(slot2));

        lockerController.deAllocateSlot(slot2);
        Assertions.assertTrue(lockerController.getAvailableSlots().contains(slot2));
        Assertions.assertTrue(lockerController.getAvailableSlots().contains(slot1));
    }

    @Test(expected = NoSlotsAvailableException.class)
    public void TestNoSlotsAvailableException() {
        //No lockers/slots have been created, this should throw exception
        this.orderController.allocateLocker(RandomUtils.randomBuyer(), new Package(new Size(12, 12), RandomUtils.randomString()));
    }




}
