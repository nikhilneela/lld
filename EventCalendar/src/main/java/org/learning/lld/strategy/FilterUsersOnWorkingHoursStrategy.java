package org.learning.lld.strategy;

import lombok.NonNull;
import org.learning.lld.models.User;

import java.util.List;

public class FilterUsersOnWorkingHoursStrategy implements IFilterUsersStrategy {
    @Override
    public List<User> filterUsers(@NonNull List<User> users) {
        return null;
    }
}
