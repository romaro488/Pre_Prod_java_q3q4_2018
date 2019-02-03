package com.epam.polosmak.task2;

import java.util.*;

public class COWContainer<T> implements List<T> {
    private static final int NO_SUCH_ELEMENT = -1;
    private Object[] container;

    public COWContainer() {
        container = new Object[0];
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) container[index];
    }

    @Override
    public T set(int index, T element) {
        checkRange(index);
        T[] temp = (T[]) container;
        T resultObj = temp[index];
        temp[index] = element;
        return resultObj;
    }

    @Override
    public void add(int index, T element) {
        checkRange(index);
        Object[] temp = container;
        if (size() == container.length)
            temp = Arrays.copyOf(temp, size() + 1);
        Object[] temp1 = Arrays.copyOfRange(container, index, size());
        int position = 0;
        temp[index] = element;
        for (int i = index + 1; i < size(); i++) {
            temp[i] = temp1[position++];
        }
        container = temp;
    }

    public boolean add(T element) {
        Object[] temp = Arrays.copyOf(container, size() + 1);
        temp[size()] = element;
        container = temp;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        addAll(size(), c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c);
        checkRange(index);
        Object[] temp = new Object[c.size() + size()];
        System.arraycopy(container, 0, temp, 0, size() - index + 1);
        System.arraycopy(c.toArray(), 0, temp, index, c.size());
        System.arraycopy(container, index, temp, temp.length - c.size(), size() - index);
        container = temp;
        return true;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T elementToRemove = ((T) container[index]);
        Object[] temp = container;
        System.arraycopy(temp, index + 1, temp, index, size() - 1 - index);
        container = temp;
        return elementToRemove;
    }

    @Override
    public boolean remove(Object element) {
        int indexOfElement = indexOf(element);
        if (indexOfElement != NO_SUCH_ELEMENT) {
            remove(indexOfElement);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object object : c) {
            if (contains(object)) {
                remove(object);
                flag = true;
            }
        }
        return flag;
    }

    private void checkRange(int index) {
        if (index < 0 || index > container.length) {
            throw new IndexOutOfBoundsException("index out of bound. index < 0 || index >= size");
        }
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != NO_SUCH_ELEMENT;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < container.length; i++) {
            if (container[i].equals(o)) {
                return i;
            }
        }
        return NO_SUCH_ELEMENT;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag = false;
        for (Object object : c) {
            if (!contains(object)) {
                remove(object);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        container = new Object[0];
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i <= 0; i++) {
            if (o.equals(container[i])) {
                return i;
            }
        }
        return NO_SUCH_ELEMENT;
    }

    @Override
    public Object[] toArray() {
        return container;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        Objects.requireNonNull(a);
        return (E[]) toArray();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl<T>((T[]) container);
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("This operation isn't supported");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("This operation isn't supported");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This operation isn't supported");
    }

    private class IteratorImpl<T> implements Iterator<T> {

        private int position = 0;
        private T[] COWArray;

        IteratorImpl(T[] COWArray) {
            this.COWArray = COWArray;
        }

        @Override
        public boolean hasNext() {
            return position <= COWArray.length - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Function hasNext return false");
            }
            return COWArray[position++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
