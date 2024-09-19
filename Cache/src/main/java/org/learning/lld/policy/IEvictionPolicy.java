package org.learning.lld.policy;

public interface IEvictionPolicy<Key> {
    Key getEvictKey();
    void keyAccessed(Key key);
}
