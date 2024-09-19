package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;

public class RegistrationNumber {
    //TODO : Can be a modeled as more complex object with state code, district code, area code and so on
    @Getter
    private final String number;

    public RegistrationNumber(@NonNull final String number) {
        this.number = number;
    }
}
