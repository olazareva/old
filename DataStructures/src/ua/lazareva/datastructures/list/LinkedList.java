package ua.lazareva.datastructures.list;


public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T object) {
        add(object, size);
    }

    @Override
    public void add(T object, int index) {
        validateIndexToAdd(index);
        Node<T> nodeToAdd = new Node<>(object);

        if (size == 0) {
            head = tail = nodeToAdd;
        } else if (index == 0) {
            nodeToAdd.next = head;
            head.prev = nodeToAdd;
            head = nodeToAdd;
        } else if (index == size) {
            tail.next = nodeToAdd;
            nodeToAdd.prev = tail;
            tail = nodeToAdd;
        } else {
            Node<T> nodeToReplace;
            nodeToReplace = getNode(index);

            nodeToAdd.prev = nodeToReplace.prev;
            nodeToAdd.next = nodeToReplace;

            nodeToReplace.prev.next = nodeToAdd;
            nodeToReplace.prev = nodeToAdd;
        }
        size++;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return getNode(index).value;
    }


    @Override
    public void remove(T object) {
        Node<T> nodeToRemove = head;

        for (int index = 0; index < size; index++) {
            if (object.equals(nodeToRemove.value)) {
                if (size == 1) {
                    head = tail = null;
                } else if (index == 0) {
                    head = head.next;
                    head.prev = null;
                } else if (index == size - 1) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    nodeToRemove.prev.next = nodeToRemove.next;
                    nodeToRemove.next.prev = nodeToRemove.prev;
                }
                size--;
                break;
            }
            nodeToRemove = nodeToRemove.next;
        }
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> nodeToRemove;

        if (size == 1) {
            nodeToRemove = head;
            head = tail = null;
        } else if (index == 0) {
            nodeToRemove = head;
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            nodeToRemove = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            nodeToRemove = getNode(index);
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
        size--;
        return nodeToRemove.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(T object, int index) {
        validateIndex(index);
        getNode(index).value = object;
    }

    @Override
    public boolean contains(T object) {
        return indexOf(object) != -1;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int indexOf(T object) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (object.equals(node.value)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T object) {
        Node<T> node = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(node.value)) {
                return i;
            }
            node = node.prev;
        }
        return -1;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            String msg = "Index " + index + " should be between 0 and " + size + " exclusive";
            throw new IndexOutOfBoundsException(msg);
        }
    }

    private void validateIndexToAdd(int index) {
        if (index < 0 || index > size) {
            String msg = "Index " + index + " should be between 0 and " + size + " inclusive";
            throw new IndexOutOfBoundsException(msg);
        }
    }

    private Node<T> getNode(int index) {
        Node<T> node;

        if (index <= size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T value;

        private Node(T obj) {
            value = obj;
        }

    }
}
