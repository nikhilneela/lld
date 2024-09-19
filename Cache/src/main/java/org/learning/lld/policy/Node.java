package org.learning.lld.policy;

public class Node<Key> {
    Key data;
    Node<Key> left, right;

    public Node(Key data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }
}