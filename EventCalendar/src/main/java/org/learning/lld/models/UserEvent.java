package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserEvent implements Comparable<UserEvent> {
    private final User user;
    private final Event event;
    private final TimeSlot timeSlot;

    @Override
    public int compareTo(UserEvent o) {
        //return -ve if this is less than o
        //return 0 if this is equal to o
        //return +ve if this is greater than o
        return this.timeSlot.compareTo(o.timeSlot);
    }
}
