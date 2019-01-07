package com.scientist.quickmvvm;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LiveDataList<E>  extends LiveData<List<E>> implements List<E> {

    private List<E> mData;

    public LiveDataList(List<E> data) {
        mData = data;
        setValue(data);
    }

    @Override
    public int size() {
        return mData.size();
    }

    @Override
    public boolean isEmpty() {
        return mData.isEmpty();
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return mData.contains(o);
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return mData.iterator();
    }

    @Nullable
    @Override
    public Object[] toArray() {
        return mData.toArray();
    }

    @Override
    public <T> T[] toArray(@Nullable T[] a) {
        return mData.toArray(a);
    }

    @Override
    public boolean add(E e) {
        boolean add = mData.add(e);
        setValue(mData);
        return add;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        boolean remove = mData.remove(o);
        setValue(mData);
        return remove;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return mData.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        boolean addAll = mData.addAll(c);
        setValue(mData);
        return addAll;
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends E> c) {
        boolean a = mData.addAll(c);
        setValue(mData);
        return a;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        boolean re = mData.removeAll(c);
        setValue(mData);
        return re;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        boolean ret = mData.retainAll(c);
        setValue(mData);
        return ret;
    }

    @Override
    public void clear() {
        mData.clear();
        setValue(mData);
    }

    @Override
    public E get(int index) {
        return mData.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = mData.set(index, element);
        setValue(mData);
        return e;
    }

    @Override
    public void add(int index, E element) {
        mData.add(index, element);
        setValue(mData);
    }

    @Override
    public E remove(int index) {
        E e = mData.remove(index);
        setValue(mData);
        return e;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return mData.indexOf(o);
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return mData.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return mData.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return mData.listIterator(index);
    }

    @NonNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return mData.subList(fromIndex, toIndex);
    }
}
