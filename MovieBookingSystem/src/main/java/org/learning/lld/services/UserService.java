package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchUserException;
import org.learning.lld.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private final Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User createUser(@NonNull final String userName) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, userName);
        users.put(userId, user);
        return user;
    }

    public User getUser(@NonNull final String userId) {
        if (!users.containsKey(userId)) {
            throw new NoSuchUserException();
        }
        return users.get(userId);
    }
}
