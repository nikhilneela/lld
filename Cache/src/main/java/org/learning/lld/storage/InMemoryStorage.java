package org.learning.lld.storage;

import lombok.NonNull;
import org.learning.lld.Exception.KeyNotFoundException;
import org.learning.lld.Exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<Key, Value> implements IStorage<Key, Value> {

    private final Map<Key, Value> hashMap;
    private final Integer capacity;

    public InMemoryStorage(@NonNull final Integer capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
    }

    @Override
    public Value get(Key key) throws KeyNotFoundException {
        if (!hashMap.containsKey(key)) {
            throw new KeyNotFoundException();
        }
        return hashMap.get(key);
    }

    @Override
    public void set(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) {
            throw new StorageFullException();
        }
        hashMap.put(key, value);
    }

    @Override
    public void remove(Key key) {
        hashMap.remove(key);
    }

    private boolean isStorageFull() {
        return hashMap.size() == this.capacity;
    }
}
