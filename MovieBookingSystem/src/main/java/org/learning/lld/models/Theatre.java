package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;
import org.learning.lld.exceptions.ScreenAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    @Getter
    private final String id;
    private final String name;
    private final List<Screen> screens;

    public Theatre(@NonNull final String id, @NonNull final String name) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(@NonNull final Screen screen) {
        //check if screen already exists
        if (this.screens.stream().anyMatch(s -> s.getId().equals(screen.getId()))) {
            throw new ScreenAlreadyExistsException();
        }
        this.screens.add(screen);
    }
}
