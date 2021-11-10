package ua.com.alevel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyUniqueList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 10;
    Object[] data;
    private int size;

    public MyUniqueList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Unsuitable index: " + index);
        }
    }

    public void remove(int index) {
        Object[] es = data;
        int newSize = size - 1;
        if (newSize > index)
            System.arraycopy(es, index + 1, es, index, newSize - index);
        es[size = newSize] = null;
    }

    public boolean remove(Object o) {
        Object[] es = data;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (es[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(es[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void add(E e) {
        if (!contains(e)) {
            if (size == data.length) {
                data = grow();
            }
            data[size] = e;
            size++;
        }
    }

    public boolean contains(Object o) {
        Object[] es = data;
        for (int i = 0; i < size; i++) {
            if (es[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    private Object[] grow() {
        int oldCapacity = data.length;
        return Arrays.copyOf(data, oldCapacity + (oldCapacity >> 1));
    }

    public Iterator<E> iterator() {
        return new MyItr();
    }

    private class MyItr implements Iterator<E> {
        int cur = 0;

        public boolean hasNext() {
            return cur != size;
        }

        public E next() {
            int i = cur;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = data;
            cur = i + 1;
            return (E) elementData[i++];
        }
    }

    public void clear() {
        final Object[] es = data;
        for (int i = 0; i < size; i++)
            es[i] = null;
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUniqueList<?> myUniqueList = (MyUniqueList<?>) o;
        return size == myUniqueList.size && Arrays.equals(data, myUniqueList.data);
    }
}
