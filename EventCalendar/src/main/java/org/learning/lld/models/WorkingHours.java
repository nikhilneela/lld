package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class WorkingHours {
    private final LocalTime startTime;
    private final LocalTime endTime;
}
