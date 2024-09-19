package org.learning.lld.models;

import java.util.concurrent.locks.Lock;

public class Buyer extends LockerUser {
    public Buyer(Contact contact, String id) {
        super(contact, id);
    }
}
