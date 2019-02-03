package com.epam.polosmak.task1;


import java.util.*;
import java.util.function.Predicate;

public class ProductContainer<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int NO_SUCH_ELEMENT = -1;
    private Object[] container;
    private int size = 0;

    public ProductContainer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity. capacity cannot less than 0");
        }
        this.container = new Object[initialCapacity];
    }

    public ProductContainer() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    private void growCapacity(int minCapacity) {
        int oldCapacity = size;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        container = Arrays.copyOf(container, newCapacity);
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) container[index];
    }

    @Override
    public T set(int index, T element) {

        checkRange(index);
        T previousElement = get(index);
        container[index] = element;
        return previousElement;
    }

    @Override
    public void add(int index, T element) {
        if (index != size) {
            checkRange(index);
        }
        ensureArrayCapacity(size++);
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = element;
        this.size++;
    }

    @Override
    public boolean add(T element) {
        if (size == container.length) {
            container = Arrays.copyOf(container, size * DEFAULT_CAPACITY);
        }
        container[++size - 1] = element;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Objects.requireNonNull(collection);
        int sizeBefore = size;
        collection.forEach(this::add);
        return sizeBefore != size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c);
        Object[] arrayNeedToAdd = c.toArray();
        if (index != size) {
            checkRange(index);
        }
        int sizeBefore = size;
        int sizeOfArrayNeedToAdd = arrayNeedToAdd.length;
        ensureArrayCapacity(this.size + sizeOfArrayNeedToAdd);
        System.arraycopy(container, index, container, index + sizeOfArrayNeedToAdd, size - index);
        System.arraycopy(arrayNeedToAdd, 0, container, index, sizeOfArrayNeedToAdd);
        size += sizeOfArrayNeedToAdd;
        return sizeBefore != size;
    }

    private void ensureArrayCapacity(int capacity) {
        if (capacity > ((container.length * 3) >> 1)) {
            growCapacity(capacity);
        }
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T elementToRemove = get(index);
        int numbersElementNeedToMoved = size - index - 1;
        System.arraycopy(container, index + 1, container, index, numbersElementNeedToMoved);
        container[--size] = null;
        return elementToRemove;
    }

    @Override
    public boolean remove(Object element) {
        int indexOfElement = indexOf(element);
        if (indexOfElement == NO_SUCH_ELEMENT) {
            return false;
        }
        remove(indexOfElement);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        Object[] arrayNeedToRemove = collection.toArray();
        int sizeBefore = size;
        for (Object element : arrayNeedToRemove) {
            while (contains(element))
                remove(element);
        }
        return sizeBefore != size;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index out of bound. index < 0 || index >= size");
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != NO_SUCH_ELEMENT;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if ((element == null ? get(i) == null : element.equals(get(i)))) {
                return i;
            }
        }
        return NO_SUCH_ELEMENT;
    }

//    @Override
//    public int indexOf(Object element) {
//        for (int i = 0; i < size; i++) {
//            if ((element == null && container[i] == null) || (container[i] != null && container[i].equals(element))) {
//                return i;
//            }
//        }
//        return NO_SUCH_ELEMENT;
//    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        Object[] needToRetain = collection.toArray();
        int sizeBefore = size;
        int toRemoveIndex = size - 1;
        while (toRemoveIndex >= 0) {
            if (!isRetainElement(needToRetain, container[toRemoveIndex])) {
                remove(toRemoveIndex);
            }
            toRemoveIndex--;
        }
        return sizeBefore != size;
    }

    private Boolean isRetainElement(Object[] needToRetain, Object element) {
        for (Object retainElement : needToRetain) {
            if ((element == null && retainElement == null) || (retainElement != null && retainElement.equals(element))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        container = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if ((o == null && container[i] == null) || (container[i] != null && container[i].equals(o))) {
                return i;
            }
        }
        return NO_SUCH_ELEMENT;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(container, size);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(container, size, a.getClass());
        }
        System.arraycopy(container, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        Object[] arrayNeedToCheck = collection.toArray();
        for (Object element : arrayNeedToCheck) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>();
    }

    public Iterator<T> iterator(Predicate<T> predicate) {
        return new MyIterator<T>(predicate);
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("this operation isn't supported");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("this operation isn't supported");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("this operation isn't supported");
    }

    private class MyIterator<E> implements Iterator<E> {

        private Predicate<? super E> predicate = null;
        private int position = 0;
        private boolean doneNext = false;

        MyIterator() {
        }

        MyIterator(Predicate<? super E> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (noMoreCondition()) {
                return position < size;
            }
            return hasCondition();
        }

        private boolean noMoreCondition() {
            return predicate == null;
        }

        private boolean hasCondition() {
            for (int i = position; i < size; i++) {
                if (predicate.test((E) container[i])) {
                    position = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("hasNext return false");
            }
            doneNext = true;
            return (E) container[position++];
        }

        @Override
        public void remove() {
            if (!doneNext) {
                throw new IllegalStateException("next hasn't called yet");
            }
            ProductContainer.this.remove(--position);
        }
    }
}
