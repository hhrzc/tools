package com.hhrzc.tools.out;

import java.util.List;
import java.util.Objects;

/**
 * An interface representing an output parameter that holds a list of values.
 * Extends CommonInterface to provide common data transfer behaviors.
 *
 * @param <T> the type of the elements in the list.
 */
public interface CollectionOut<T> extends CommonInterface {

    /**
     * Adds one or more elements to the current list.
     *
     * @param elements one or more elements to add.
     * @throws IllegalStateException if the underlying list is not instantiated.
     */
    void add(T... elements);

    /**
     * Sets the underlying list.
     * @param tList the list to set.
     */
    void set(List<T> tList);

    /**
     * Returns the underlying list.
     * @return the list of elements (may never be null if safely instantiated).
     */
    List<T> get();

    /**
     * Set the null - means the out object wasn't applied
     */
    @Override
    default void reset() {
        set(null);
    }

    /**
     * Indicates whether the output has been "applied".
     * We consider it applied if the underlying list is instantiated
     * and (optionally) contains elements.
     *
     * @return true if the underlying list is non-null.
     */
    @Override
    default boolean isApplied() {
        return Objects.nonNull(get());
    }
}

