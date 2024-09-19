package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.User;

public interface IUserRepository {
    void createUser(@NonNull final User user);
    User getUser(@NonNull final String id);
}
