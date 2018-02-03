package ua.lazareva.datastructures.list;

public class ArrayList<T> implements List<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    ArrayList() {
        array = (T[]) new Object[5];
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public T remove(int index) {
        validateIndex(index);

        T tmpObject = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return tmpObject;
    }

    @Override
    public void remove(T object) {

        for (int index = 0; index < size; index++) {
            if (array[index].equals(object)) {
                System.arraycopy(array, index + 1, array, index, size - 1 - index);
                size--;
            }
        }
    }


    public void add(T obj, int index) {  //0<=index<=size
        validateIndexToAdd(index);

        if (size == array.length) {
            int newLength = (int) (array.length * 1.5) + 1;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[newLength];

            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T obj) {
        add(obj, size);
    }

    public int indexOf(T findObj) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(findObj)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T findObj) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(findObj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }


    public void set(T obj, int index) {
        validateIndex(index);
        array[index] = obj;
    }


    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            String msg = "Index " + index + " should be between 0 and " + size + " exclusive";
            throw new IndexOutOfBoundsException(msg);
        }
    }

    private void validateIndexToAdd(int index) {
        if (index < 0 || index > size) {
            String msg = "Index " + index + " should be between 0 and " + size + " exclusive";
            throw new IndexOutOfBoundsException(msg);
        }
    }
}