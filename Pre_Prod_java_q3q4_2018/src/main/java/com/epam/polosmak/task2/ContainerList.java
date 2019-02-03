package com.epam.polosmak.task2;

import java.util.*;

public class ContainerList<T> implements List<T> {

    private List<T> unmodifiableList;
    private List<T> modifiableList;
    private int NO_SUCH_ELEMENT = -1;

    public ContainerList(List<T> unmodifiableList, List<T> modifiableList) {
        if (unmodifiableList == null || modifiableList == null) {
            throw new IllegalArgumentException();
        }
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;
    }

    public ContainerList() {
    }

    @Override
    public int size() {
        return unmodifiableList.size() + modifiableList.size();
    }

    @Override
    public boolean isEmpty() {
        return unmodifiableList.isEmpty() && modifiableList.isEmpty();
    }

    @Override
    public boolean contains(Object needToCheck) {
        return unmodifiableList.contains(needToCheck) || modifiableList.contains(needToCheck);
    }

    @Override
    public Object[] toArray() {
        Object containerResult[] = new Object[size()];
        System.arraycopy(modifiableList.toArray(), 0, containerResult, 0, unmodifiableList.size());
        System.arraycopy(unmodifiableList.toArray(), 0, containerResult, modifiableList.size(), unmodifiableList.size());
        return containerResult;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size()) {
            return (E[]) Arrays.copyOf(toArray(), size(), a.getClass());
        }
        System.arraycopy(modifiableList.toArray(), 0, a, 0, unmodifiableList.size());
        System.arraycopy(unmodifiableList.toArray(), 0, a, modifiableList.size(), unmodifiableList.size());
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }

    @Override
    public boolean add(T elementToAdd) {
        return modifiableList.add(elementToAdd);
    }

    @Override
    public boolean remove(Object elementToRemove) {
        if (unmodifiableList.contains(elementToRemove)) {
            throw new IllegalStateException();
        }
        return modifiableList.remove(elementToRemove);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        return collection.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return modifiableList.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndexForModification(index);
        return modifiableList.addAll(getIndexForModified(index), collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        collection.forEach(needToRemove -> {
            if (unmodifiableList.contains(needToRemove)) {
                throw new IllegalStateException();
            }
        });
        return modifiableList.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return modifiableList.retainAll(collection);
    }

    @Override
    public void clear() {
        if (unmodifiableList.size() == 0) {
            modifiableList.clear();
        } else {
            throw new IllegalStateException("Cannot clear unmodifiable list");
        }
    }

    @Override
    public T get(int index) {
        checkRange(index);
        if (index < unmodifiableList.size()) {
            return unmodifiableList.get(index);
        }
        return modifiableList.get(getIndexForModified(index));
    }

    @Override
    public T set(int index, T element) {
        checkRange(index);
        checkIndexForModification(index);
        return modifiableList.set(getIndexForModified(index), element);
    }


    @Override
    public void add(int index, T element) {
        checkIndexForModification(index);
        modifiableList.add(getIndexForModified(index), element);
    }

    private int getIndexForModified(int index) {
        if (index >= unmodifiableList.size()) {
            return index - unmodifiableList.size();
        }
        return index;
    }

    private void checkIndexForModification(int index) {
        if (index < unmodifiableList.size()) {
            throw new IllegalStateException("Index cannot less than unmodifiable list size. Index > "
                    + unmodifiableList.size() + " index <= " + size());
        }
        if (index != size()) {
            checkRange(index);
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index > 0 && index < " + size());
        }
    }

    @Override
    public T remove(int index) {
        checkIndexForModification(index);
        checkRange(index);
        return modifiableList.remove(getIndexForModified(index));
    }

    @Override
    public int indexOf(Object checkelEment) {
        int index = unmodifiableList.indexOf(checkelEment);
        if (index != NO_SUCH_ELEMENT) {
            return index;
        }
        if (modifiableList.indexOf(checkelEment) != NO_SUCH_ELEMENT) {
            return modifiableList.indexOf(checkelEment) + unmodifiableList.size();
        }
        return NO_SUCH_ELEMENT;
    }

    @Override
    public int lastIndexOf(Object elementNeedToCheck) {
        int index = unmodifiableList.lastIndexOf(elementNeedToCheck);
        if (index != NO_SUCH_ELEMENT)
            return index;
        if (modifiableList.indexOf(elementNeedToCheck) != NO_SUCH_ELEMENT) {
            return modifiableList.indexOf(elementNeedToCheck) + unmodifiableList.size();
        }
        return NO_SUCH_ELEMENT;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl<T>();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    class IteratorImpl<E> implements Iterator<E> {

        private int position;
        private boolean doneNext = false;

        public IteratorImpl() {
        }

        @Override
        public boolean hasNext() {
            return position != size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            doneNext = true;
            return (E) get(position++);
        }

        public void remove() {
            if (!doneNext) {
                throw new IllegalStateException();
            }
            ContainerList.this.remove(--position);
        }
    }
}
