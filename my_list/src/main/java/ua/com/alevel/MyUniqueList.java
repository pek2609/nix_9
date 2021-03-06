package ua.com.alevel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyUniqueList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;
    private int maxCapacity;

    protected void setSize(int size) {
        this.size = size;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public MyUniqueList() {
        maxCapacity = -1;
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyUniqueList(E[] es) {
        maxCapacity = -1;
        data = new Object[DEFAULT_CAPACITY];
        for (E e : es) {
            add(e);
        }
    }

    public MyUniqueList(int maxCapacity) {
        data = new Object[DEFAULT_CAPACITY];
        this.maxCapacity = maxCapacity;
        size = 0;
    }

    protected void checkIndexes(int firstIndex, int lastIndex) {
        checkIndex(firstIndex);
        checkIndex(lastIndex);
        if (lastIndex - firstIndex < 0) {
            throw new RuntimeException("Unexpect firstIndex = " + firstIndex + ", lastIndex = " + lastIndex);
        }
    }

    protected void checkIndex(int index) {
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
            if (maxCapacity == size) {
                throw new RuntimeException("Your set is full, maxCapacity = " + maxCapacity + " , actually size = " + size);
            }
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
        Object[] res = new Object[size];
        System.arraycopy(data, 0, res, 0, size);
        return res;
    }

    private Object[] grow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] res = new Object[newCapacity];
        System.arraycopy(data, 0, res, 0, size);
        return res;
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

    public String toString() {
        Iterator<E> it = iterator();
        StringBuilder sb = new StringBuilder();
        if (size() == 0) {
            return "[]";
        }
        sb.append("[");
        while (true) {
            E e = it.next();
            sb.append(e.toString());
            if (!it.hasNext()) {
                return sb.append("]").toString();
            }
            sb.append(",").append(" ");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUniqueList<?> myUniqueList = (MyUniqueList<?>) o;
        return size == myUniqueList.size && Arrays.equals(data, myUniqueList.data);
    }
}
