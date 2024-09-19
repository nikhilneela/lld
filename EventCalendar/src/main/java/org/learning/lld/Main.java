package org.learning.lld;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalTime start = LocalTime.of(10, 30);
        LocalTime end = LocalTime.of(22, 30);

        System.out.println(start.isBefore(end));
    }
}