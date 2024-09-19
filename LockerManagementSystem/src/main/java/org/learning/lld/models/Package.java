package org.learning.lld.models;

public class Package implements LockerItem {
    private final Size size;
    private final String id;

    public Package(final Size size, final String id) {
        this.size = size;
        this.id = id;
    }

    @Override
    public Size getSize() {
        return size;
    }
}
