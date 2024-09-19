package org.learning.lld.strategy;

import lombok.NonNull;
import org.learning.lld.models.User;

import java.util.List;

public interface IFilterUsersStrategy {
    List<User> filterUsers(@NonNull final List<User> users);
}
