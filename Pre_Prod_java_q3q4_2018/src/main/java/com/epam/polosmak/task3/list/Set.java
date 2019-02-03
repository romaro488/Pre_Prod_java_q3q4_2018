package com.epam.polosmak.task3.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Set<T> extends ArrayList<T> {

    public Set() {
        super();
    }

    public Set(Collection<? extends T> collection) {
        Objects.requireNonNull(collection);
        HashSet<? extends T> set = new HashSet<>(collection);
        if (set.size() != collection.size()) {
            throw new IllegalArgumentException("Cannot load a list with collection which contains duplicate elements");
        }
        super.addAll(collection);
    }

    @Override
    public boolean add(T element) {
        checkElement(element);
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        checkElement(element);
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (Object o : collection) {
            checkElement(o);
        }
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        for (Object o : collection) {
            checkElement(o);
        }
        return super.addAll(index, collection);
    }

    @Override
    public T set(int index, T element) {
        checkElement(element);
        return super.set(index, element);
    }

    private void checkElement(Object o) {
        if (this.contains(o)) {
            throw new IllegalArgumentException("Element is exist");
        }
    }
}
