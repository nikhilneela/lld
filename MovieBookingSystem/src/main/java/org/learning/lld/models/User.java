package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {
    @Getter
    private final String id;
    private final String name;
}
