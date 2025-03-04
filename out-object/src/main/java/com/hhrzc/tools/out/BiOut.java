package com.hhrzc.tools.out;

import java.util.Objects;

/**
 * A bi-directional data transfer object interface.
 *
 * @param <T> the type of the first value
 * @param <K> the type of the second value
 */
public interface BiOut<T, K> extends CommonInterface {

    /**
     * Sets the first value.
     *
     * @param t the first value
     */
    void setFirst(T t);

    /**
     * Sets the second value.
     *
     * @param k the second value
     */
    void setSecond(K k);

    /**
     * Gets the first value.
     *
     * @return the first value
     */
    T getFirst();

    /**
     * Gets the second value.
     *
     * @return the second value
     */
    K getSecond();

    @Override
    default void reset() {
        setFirst(null);
        setSecond(null);
    }

    @Override
    default boolean isApplied() {
        return Objects.nonNull(getFirst()) && Objects.nonNull(getSecond());
    }
}