package com.hhrzc.tools.out;

/**
 * Common interface for data transfer objects.
 */
public interface CommonInterface {

    /**
     * Resets the state of the object.
     */
    void reset();

    /**
     * Checks if the data transfer object is applied.
     *
     * @return true if the (all) data transfer object(s) is applied, false otherwise
     */
    boolean isApplied();
}
