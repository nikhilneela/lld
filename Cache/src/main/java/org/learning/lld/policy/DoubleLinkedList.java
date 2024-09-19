package org.learning.lld.policy;

import lombok.NonNull;
public class DoubleLinkedList<Key> {
    private Node<Key> head;
    private Node<Key> tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public Node<Key> addFront(@NonNull final Key data) {
        Node<Key> newNode = new Node<Key>(data);

        if (this.head != null) {
            newNode.right = this.head;
            this.head.left = newNode;
        } else {
            this.tail = newNode;
        }
        this.head = newNode;
        return newNode;
    }

    public void addFront(@NonNull final Node<Key> node) {
        if (this.head != null) {
            node.right = this.head;
            this.head.left = node;
        } else {
            this.tail = node;
        }
        this.head = node;
    }

    public void remove(@NonNull final Node<Key> nodeToRemove) {
        Node<Key> left = nodeToRemove.left;
        Node<Key> right = nodeToRemove.right;

        nodeToRemove.left = null;
        nodeToRemove.right = null;

        if (left != null && right != null) {
            left.right = right;
            right.left = left;
        } else if (left != null) {
            left.right = null;
        } else if (right != null){
            right.left = null;
            this.head = right;
        } else {
            this.head = null;
        }

        if (nodeToRemove == tail) {
            tail = left;
        }
    }

    public Key removeLast() {
        Key key = this.tail.data;
        remove(this.tail);
        return key;
    }

    public int size() {
        int size = 0;
        Node<Key> headRef = this.head;
        while (headRef != null) {
            headRef = headRef.left;
            size++;
        }
        return size;
    }
}
