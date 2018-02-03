package ua.lazareva.datastructures.queue;


public class LinkedQueue<E> extends AbstractQueueSizeControl implements Queue<E> {
    private Node<E> head;
    private Node<E> tail;

    @SuppressWarnings("unchecked")
    public LinkedQueue(int size) {
        maxQueueSize = size;
    }

    @Override
    public void push(E value) {
        if (value != null && !value.equals("")) {
            checkAvailableCapacity();
            Node<E> nodeToAdd = new Node<>(value);
            if (size == 0) {
                tail = head = nodeToAdd;
            } else {
                tail.next = nodeToAdd;
                nodeToAdd.prev = tail;
                tail = nodeToAdd;
            }
            size++;
        } else {
            throw new IllegalArgumentException("NULLs not allowed to push into queue");
        }
    }

    @Override
    public E peek() {
        if (size > 0) {
            return head.value;
        } else {
            throw new IndexOutOfBoundsException("Queue is empty. Nothing to peek.");
        }
    }

    @Override
    public E poll() {
        Node<E> nodeToRemove = head;
        if (size == 1) {
            head = tail = null;
        } else if (size > 1) {
            head = head.next;
            head.prev = null;
        } else {
            throw new IndexOutOfBoundsException("Queue is empty. Nothing to remove.");
        }
        size--;
        return nodeToRemove.value;
    }

    private class Node<E> {
        private Node<E> prev;
        private Node<E> next;
        private E value;

        private Node(E value) {
            this.value = value;
        }
    }
}
