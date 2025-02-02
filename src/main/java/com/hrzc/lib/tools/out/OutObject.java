package com.hrzc.lib.tools.out;

import java.util.Objects;

public class OutObject<T> implements Out<T> {
    private T t;
    @Override
    public T get() {
        return t;
    }

    @Override
    public void set(T t) {
        this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutObject<?> outObject)) return false;
        return Objects.equals(t, outObject.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t);
    }
}
