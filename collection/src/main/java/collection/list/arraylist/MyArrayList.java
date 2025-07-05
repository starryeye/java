package collection.list.arraylist;

import collection.list.MyList;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size = 0;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends E> c) {
        elements = new Object[c.size()];
        int i = 0;
        for (E e : c) {
            elements[i++] = e;
        }
    }

    @Override
    public void add(E e) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = e;
    }

    @Override
    public void add(int index, E e) {
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = e;
        size++;
    }

    @Override
    public E set(int index, E e) {
        E old = get(index);
        elements[index] = e;
        return old;
    }

    @Override
    public E remove(int index) {
        E e = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements) + " size=" + size + ", capacity=" + elements.length;
    }

    private void grow() {
        elements = Arrays.copyOf(elements, 2 * elements.length);
    }
}
