package com.hhrzc.tools.out.impl;

import com.hhrzc.tools.out.CollectionOut;
import com.hhrzc.tools.out.exceptions.ObjectIsNotInstantiatedException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ArrayListOut<T> implements CollectionOut<T> {
    private List<T> tList;

    @Override
    public void add(T... t) {
        if (Objects.isNull(tList)) {
            throw new ObjectIsNotInstantiatedException(
                    "List is not instantiated. Please use set() method to instantiate a list before using add()."
            );
        }
        tList.addAll(Arrays.asList(t));
    }

    @Override
    public void set(List<T> tList) {
        this.tList = tList;
    }

    @Override
    public List<T> get() {
        return tList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayListOut<?> that = (ArrayListOut<?>) o;
        return Objects.equals(tList, that.tList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tList);

    }
}