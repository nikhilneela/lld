package org.learning.lld.models;

import lombok.Getter;

public abstract class LockerUser {
    @Getter
    private String id;
    @Getter
    private final Contact contact;

    public LockerUser(Contact contact, String id) {
        this.contact = contact;
        this.id = id;
    }
}
