package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class SeatLock {
    private final Seat seat;
    private final Show show;
    private final Date lockedAt;
    private final Integer timeoutInSeconds;
    private final User lockedBy;

    public boolean isLockExpired() {
        Date currentDate = new Date();
        long elapsedTimeInMillis = currentDate.getTime() - lockedAt.getTime();
        return (elapsedTimeInMillis / 1000) - timeoutInSeconds > 0;
    }

}
