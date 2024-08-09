package com.hrzc.lib.tools.out;

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
}
