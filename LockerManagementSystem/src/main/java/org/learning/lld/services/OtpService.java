package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.models.Slot;
import org.learning.lld.repositories.ISlotOtpRepository;
import org.learning.lld.strategies.IOtpGenerator;

public class OtpService {
    private final IOtpGenerator otpGenerator;
    private final ISlotOtpRepository slotOtpRepository;

    public OtpService(@NonNull final IOtpGenerator otpGenerator, ISlotOtpRepository slotOtpRepository) {
        this.otpGenerator = otpGenerator;
        this.slotOtpRepository = slotOtpRepository;
    }

    public String generateOtp(@NonNull final Slot slot) {
        final String otp = otpGenerator.generateOtp();
        this.slotOtpRepository.addOtp(slot, otp);
        return otp;
    }

    public boolean validateOtp(@NonNull final String otp, @NonNull final Slot slot) {
        final String savedOtp = this.slotOtpRepository.getOtp(slot);
        return savedOtp != null && savedOtp.equals(otp);
    }
}
