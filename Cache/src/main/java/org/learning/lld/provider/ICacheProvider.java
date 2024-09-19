package org.learning.lld.provider;

public interface ICacheProvider<Key, Value> {
    Value get(Key key);
    void set(Key key, Value value);
}
