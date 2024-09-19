package org.learning.lld.strategies;

import lombok.NonNull;
import org.learning.lld.models.Slot;

import java.util.List;

public class RandomSlotAssignmentStrategy implements ISlotAssignmentStrategy {

    private final IRandomNumberGenerator randomNumberGenerator;

    public RandomSlotAssignmentStrategy(@NonNull final IRandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public Slot assignSlot(List<Slot> slots) {

        if (slots.isEmpty()) {
            return null;
        }

        int random = this.randomNumberGenerator.getRandomNumber(slots.size());
        return slots.get(random);
    }
}
