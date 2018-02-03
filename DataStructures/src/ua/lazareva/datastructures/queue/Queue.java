package ua.lazareva.datastructures.queue;


public interface Queue<E> {

    int getSize();

    void push(E value);

    E peek();

    E poll();
}
