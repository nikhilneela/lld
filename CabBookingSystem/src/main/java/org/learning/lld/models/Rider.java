package org.learning.lld.models;

import lombok.Getter;

public class Rider {
    @Getter
    private final String id;

    public Rider(String id) {
        this.id = id;
    }
}
