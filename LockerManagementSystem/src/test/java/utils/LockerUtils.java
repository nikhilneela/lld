package utils;

import lombok.NonNull;
import org.learning.lld.controllers.LockerController;
import org.learning.lld.models.Locker;
import org.learning.lld.models.Size;
import org.learning.lld.models.Slot;

import java.util.List;

public class LockerUtils {
    public static void createLockerWithSlots(@NonNull final LockerController lockerController, @NonNull final List<Size> slotSizes) {
        Locker locker = lockerController.createLocker(RandomUtils.randomString());

        for (Size slotSize : slotSizes) {
            locker.addSlot(new Slot(RandomUtils.randomString(), new Size(slotSize.getWidth(), slotSize.getHeight())));
        }
    }
}
