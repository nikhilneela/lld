package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchUserException;
import org.learning.lld.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserInMemoryRepository implements IUserRepository {
    private Map<String, User> users;

    public UserInMemoryRepository() {
        this.users = new HashMap<>();
    }

    @Override
    public void createUser(@NonNull User user) {

        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
    }

    @Override
    public User getUser(@NonNull String id) {
        if (!users.containsKey(id)) {
            throw new NoSuchUserException();
        }
        return users.get(id);
    }
}
