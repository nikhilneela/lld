package org.learning.lld.strategies;

import lombok.NonNull;

public class OtpGeneratorRandom implements IOtpGenerator {
    private final int otpLength;
    private final IRandomNumberGenerator randomNumberGenerator;

    public OtpGeneratorRandom(@NonNull final Integer otpLength, @NonNull final IRandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.otpLength = otpLength;
    }

    @Override
    public String generateOtp() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            builder.append(randomNumberGenerator.getRandomNumber(10));
        }
        return builder.toString();
    }
}
