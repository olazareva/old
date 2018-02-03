package ua.lazareva.datastructures;

import java.util.ArrayList;


public class HashMap<K, V> {

    private ArrayList<Entry<K, V>>[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        array = new ArrayList[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = new ArrayList<>();
        }
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        for (Entry<K, V> entry : array[index]) {
            if ((key == null && entry.key == null) || key.equals(entry.key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < array.length; i++) {
            for (Entry<K, V> entry : array[i]) {
                if ((value == null && entry.value == null) || value.equals(entry.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        for (Entry<K, V> entry : array[index]) {
            if ((key == null && entry.key == null) || key.equals(entry.key)) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        ensureCapacity();
        int index = getBucketIndex(key);
        ArrayList<Entry<K, V>> bucket = array[index];
        for (Entry<K, V> entry : bucket) {
            if ((key == null && entry.key == null) || key.equals(entry.key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
        return value;
    }


    private void ensureCapacity() {
        if (size > array.length * 0.75) {
            @SuppressWarnings("unchecked")
            ArrayList<Entry<K, V>>[] arrayExtended = new ArrayList[array.length * 2];
            for (int i = 0; i < arrayExtended.length; i++) {
                arrayExtended[i] = new ArrayList<>();
            }
            ArrayList<Entry<K, V>>[] arrayOld = array;
            array = arrayExtended;
            size = 0;
            for (int i = 0; i < arrayOld.length; i++) {
                for (Entry<K, V> entry : arrayOld[i]) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        ArrayList<Entry<K, V>> bucket = array[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if ((key == null && entry.key == null) || key.equals(entry.key)) {
                V tmpObject = entry.value;
                bucket.remove(i);
                size--;
                return tmpObject;
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public V putIfAbsent(K key, V value) {

        if (!containsKey(key)) {
            int index = getBucketIndex(key);
            ArrayList<Entry<K, V>> bucket = array[index];
            Entry<K, V> entry = new Entry<>(key, value);
            bucket.add(entry);
            size++;
            return entry.value;
        }
        return null;
    }

    public void putAll(HashMap<K, V> map) {
        for (int i = 0; i < map.array.length; i++) {
            for (Entry<K, V> entry : map.array[i]) {
                put(entry.key, entry.value);
            }
        }
    }

    public V putAllIfAbsent(HashMap<K, V> map) {
        for (int i = 0; i < map.array.length; i++) {
            for (Entry<K, V> entry : map.array[i]) {
                putIfAbsent(entry.key, entry.value);
            }
        }
        return null;
    }

    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % array.length);

    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
