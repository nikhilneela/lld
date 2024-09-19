package org.learning.lld.policy;

import java.security.Key;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements IEvictionPolicy<Key> {
    private final DoubleLinkedList<Key> orderedLinkedList;
    private final Map<Key, Node<Key>> lookupMap;

    public LRUEvictionPolicy() {
        this.orderedLinkedList = new DoubleLinkedList<Key>();
        this.lookupMap = new HashMap<Key, Node<Key>>();
    }

    @Override
    public Key getEvictKey() {
        return orderedLinkedList.removeLast();
    }

    @Override
    public void keyAccessed(Key key) {
        if (!lookupMap.containsKey(key)) {
            Node<Key> node = orderedLinkedList.addFront(key);
            lookupMap.put(key, node);
        } else {
            Node<Key> node = lookupMap.get(key);
            orderedLinkedList.remove(node);
            orderedLinkedList.addFront(node);
        }
    }
}
