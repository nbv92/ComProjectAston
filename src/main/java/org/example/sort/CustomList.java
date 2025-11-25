package org.example.sort;

public class CustomList<T> {
    public static final int DEFAULT_SIZE = 10;
    private Object[] elements;
    private int size = 0;

    public CustomList() {
        this.elements = new Object[DEFAULT_SIZE];
    }

    public int size() {
        return size;
    }

    public void add(T obj) {
        shouldThisBeExpanded();
        elements[size++] = obj;
    }

    public void add(int index, T obj) {
        checkBounds(index);
        shouldThisBeExpanded();

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = obj;
        size++;
    }

    public void set(int index, T obj) {
        checkBounds(index);
        elements[index] = obj;
    }

    public T get(int index) {
        checkBounds(index);
        return (T) elements[index];
    }

    public T remove(int index) {
        checkBounds(index);
        T removed = (T) elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return removed;
    }

    public void addAll(T[] array) {
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    public void addAll(Iterable<T> collection) {
        for (T item : collection) {
            add(item);
        }
    }

    private void shouldThisBeExpanded() {
        if (size == elements.length) expandArray();
    }

    private void expandArray() {
        int newCapacity = elements.length + (elements.length >> 1);
        Object[] newArray = new Object[newCapacity];

        for (int i = 0; i < elements.length; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}