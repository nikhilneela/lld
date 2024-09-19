package org.learning.lld.models;

public class Cab {
    private final String id;
    private final String registrationNumber;
    private final Color color;
    private final Driver driver;

    public Cab(String id, String registrationNumber, Color color, Driver driver) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.driver = driver;
    }
}
