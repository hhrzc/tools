package com.hhrzc.tools.out.impl;

import com.hhrzc.tools.out.EqualsOut;

import java.util.Objects;

public class EqualsOutObjects<T> implements EqualsOut<T> {

    private T t1, t2;

    @Override
    public void setFirst(T t) {
        t1 = t;
    }

    @Override
    public void setSecond(T t) {
        t2 = t;
    }

    @Override
    public T getFirst() {
        return t1;
    }

    @Override
    public T getSecond() {
        return t2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsOutObjects<?> biOut = (EqualsOutObjects<?>) o;
        return Objects.equals(t1, biOut.t1) &&
                Objects.equals(t2, biOut.t2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t1, t2);
    }

    @Override
    public String toString() {
        return "EqualsOutObjects{" +
                "first=" + t1 +
                ", second=" + t2 +
                '}';

    }
}