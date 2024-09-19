package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Team {
    private final String id;
    private final String name;
    private final List<User> users;
}
