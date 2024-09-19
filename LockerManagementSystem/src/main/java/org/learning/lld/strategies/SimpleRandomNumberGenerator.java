package org.learning.lld.strategies;

import java.util.Random;

public class SimpleRandomNumberGenerator implements IRandomNumberGenerator {
    private final Random random;

    public SimpleRandomNumberGenerator() {
        this.random = new Random();
    }

    @Override
    public int getRandomNumber(int max) {
        return this.random.nextInt(max);
    }
}
