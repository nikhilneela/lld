package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import java.util.Date;

@AllArgsConstructor
@Getter
public class Show {
    private final String id;
    private final Movie movie;
    private final Date time;
    private final Integer durationInSeconds;
    private final Screen screen;
}
