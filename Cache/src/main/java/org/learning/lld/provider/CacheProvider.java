package org.learning.lld.provider;

import lombok.NonNull;
import org.learning.lld.Exception.StorageFullException;
import org.learning.lld.policy.IEvictionPolicy;
import org.learning.lld.storage.IStorage;

public class CacheProvider<Key, Value> implements ICacheProvider<Key, Value> {
    private final IStorage<Key, Value> storage;
    private final IEvictionPolicy<Key> evictionPolicy;

    public CacheProvider(@NonNull final IStorage<Key, Value> storage, @NonNull final IEvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public Value get(Key key) {
        return storage.get(key);
    }

    @Override
    public void set(Key key, Value value) {
        try {
            storage.set(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException storageFullException) {
            Key keyToRemove =  evictionPolicy.getEvictKey();
            storage.remove(keyToRemove);
            set(key, value);
        }
    }
}
