package ua.com.alevel;

import java.util.Arrays;

public class MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    Object[] data;
    private int size;

    public MyList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private Object[] grow() {
        int oldCapacity = data.length;
        return Arrays.copyOf(data, oldCapacity + (oldCapacity >> 1));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Unsuitable index: " + index);
        }
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public void add(E e) {
        if (size == data.length) {
            data = grow();
        }
        data[size] = e;
        size++;
    }

    public E remove(int index) {
        checkIndex(index);
        Object[] es = data;
        E value = (E) es[index];
        int newSize = size - 1;
        if (newSize > index)
            System.arraycopy(es, index + 1, es, index, newSize - index);
        es[size = newSize] = null;
        return value;
    }

    public boolean remove(Object o) {
        Object[] es = data;
        if(o==null) {
            for (int i = 0; i < size; i++) {
                if(es[i] == null){
                    remove(i);
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if(o.equals(es[i])){
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }
}
