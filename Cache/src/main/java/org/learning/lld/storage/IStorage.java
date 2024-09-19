package org.learning.lld.storage;

import org.learning.lld.Exception.KeyNotFoundException;
import org.learning.lld.Exception.StorageFullException;

public interface IStorage<Key, Value> {
    Value get(Key key) throws KeyNotFoundException;
    void set(Key key, Value value) throws StorageFullException;
    void remove(Key key);
}
