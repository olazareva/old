package ua.lazareva.datastructures.list;

public interface List<T> {

    int size();

    void add(T object);

    void add(T object, int index);

    T get(int index);

    void remove(T object);

    T remove(int index);

    boolean isEmpty();

    void set(T object, int index);

    boolean contains(T object);

    void clear();

    int indexOf(T object);

    int lastIndexOf(T object);
}
