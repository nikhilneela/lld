package org.learning.lld.models;

import lombok.NonNull;

public class Location {
    private Integer x;
    private Integer y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(@NonNull final Location other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }
}
