package org.learning.lld.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seat {
    private String id;
    private int rowNo;
    private int colNo;
}
