package ua.lazareva.datastructures.queue;

public class ArrayQueue<E> extends AbstractQueueSizeControl implements Queue<E> {
    private E[] array;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int size) {
        maxQueueSize = size;
        array = (E[]) new Object[maxQueueSize];
    }

    @Override
    public void push(E value) {
        checkAvailableCapacity();
        if (size == 0) {
            head = tail = 0;
        } else if (size < maxQueueSize && tail < maxQueueSize - 1) {
            tail += 1;
        } else if (size < maxQueueSize && tail == maxQueueSize - 1) {
            System.arraycopy(array, head, array, 0, size);
            head = 0;
            tail = size;
            for (int i = size; i < getMaxQueueSize(); i++) {
                array[i] = null;
            }
        }
        array[tail] = value;
        size++;
    }

    @Override
    public E peek() {
        if (size != 0) {
            return array[head];
        } else {
            throw new IndexOutOfBoundsException("Queue is empty. Nothing to peek.");
        }
    }

    @Override
    public E poll() {
        if (size != 0) {
            E objToPoll = array[head];
            array[head] = null;
            head += 1;
            size--;
            return objToPoll;
        } else {
            throw new IndexOutOfBoundsException("Queue is empty. Nothing to poll.");
        }
    }
}
