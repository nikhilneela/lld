package org.learning.lld.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.services.UserService;

@AllArgsConstructor
public class UserController {
    private final UserService userService;

    public String createUser(@NonNull final String userName) {
        return this.userService.createUser(userName).getId();
    }
}
