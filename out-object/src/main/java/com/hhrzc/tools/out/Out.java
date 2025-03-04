package com.hhrzc.tools.out;

import java.util.Objects;

public interface Out<T> extends CommonInterface {
    T get();
    void set(T t);

    @Override
    default void reset() {
        set(null);
    }

    @Override
    default boolean isApplied() {
        return Objects.nonNull(get());
    }
}
