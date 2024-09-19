package org.learning.lld.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.exceptions.BadRequestException;
import org.learning.lld.services.UserService;

import java.time.LocalTime;

@AllArgsConstructor
public class UserController {
    private final UserService userService;

    public String createUser(@NonNull final String userName, @NonNull final LocalTime startTime, @NonNull final LocalTime endTime) {

        if (endTime.isBefore(startTime)) {
            throw new BadRequestException();
        }
        return this.userService.createUser(userName, startTime, endTime).getId();
    }
}
