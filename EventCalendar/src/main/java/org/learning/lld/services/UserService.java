package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.models.User;
import org.learning.lld.models.WorkingHours;
import org.learning.lld.repositories.IUserRepository;
import org.learning.lld.repositories.UserInMemoryRepository;

import java.time.LocalTime;
import java.util.UUID;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(@NonNull final IUserRepository userRepository) {
        this.userRepository = new UserInMemoryRepository();
    }

    public User createUser(@NonNull final String userName, @NonNull final LocalTime startTime, @NonNull final LocalTime endTime) {
        //assume we have done validations in the controller layer
        //Generally business specific validations are done in service layer and basic/obvious validations are done in controller layer
        //example, userName cannot contain special characters -> do it in controller layer
        //example, workingHours cannot be more than 8 hours -> do it in service layer
        //example, startTime has to be less than endTime -> do it in controller layer
        User user = new User(UUID.randomUUID().toString(), userName, new WorkingHours(startTime, endTime));
        this.userRepository.createUser(user);
        return user;
    }

    public User getUser(@NonNull final String id) {
        return this.userRepository.getUser(id);
    }
}
