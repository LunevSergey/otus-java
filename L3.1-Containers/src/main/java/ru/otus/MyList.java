package ru.otus;

import java.util.*;

public class MyList<T> implements List<T> {

    private Object[] entity;

    private static int DEFAULT_CAPACITY = 10;

    private int size = 0;

    public MyList() {
        entity = new Object[DEFAULT_CAPACITY];
    }

    public MyList(int initialSize) {
        if (initialSize > 0) {
            entity = new Object[initialSize];
        } else if (initialSize == 0) {
            entity = new Object[0];
        } else {
            throw new IllegalArgumentException("Initial size can`t be negative, but was: " + initialSize);
        }
        size = initialSize;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(entity, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size >= entity.length) {
            entity = enlarge(entity);
        }
        entity[size] = t;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index >= entity.length) {
            entity = enlarge(entity, index);
        }
        entity[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator<? extends T> iterator = c.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        entity = new Object[0];
        size = 0;
    }

    @Override
    public T get(int index) {
        return (T) entity[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        T oldElement = (T) entity[index];
        entity[index] = element;
        return oldElement;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new Literator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private Object[] enlarge(final Object[] sourse) {
        return enlarge(sourse, sourse.length);
    }

    private Object[] enlarge(final Object[] source, final int newSize) {
        Object[] result = new Object[(int) (newSize * 1.5) + 1];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i];
            size = i + 1;
        }
        return result;
    }

    private class Literator<T> implements ListIterator {

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            cursor = i + 1;
            return (T) entity[i];
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {
            entity[cursor - 1] = o;
        }

        @Override
        public void add(Object o) {

        }
    }

    public String toString() {
        Iterator<T> it = listIterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            T e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

}
