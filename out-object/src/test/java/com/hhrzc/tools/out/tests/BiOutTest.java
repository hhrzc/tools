package com.hhrzc.tools.out.tests;

import com.hhrzc.tools.out.BiOut;
import com.hhrzc.tools.out.impl.BiOutObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BiOutTest {
    @Test
    void testSetAndGet() {
        BiOutObject<String, Integer> biOut = new BiOutObject<>();
        biOut.setFirst("Alice");
        biOut.setSecond(25);

        assertEquals("Alice", biOut.getFirst());
        assertEquals(25, biOut.getSecond());
    }

    @Test
    void testEqualsAndHashCode() {
        BiOutObject<String, Integer> biOut1 = new BiOutObject<>();
        BiOutObject<String, Integer> biOut2 = new BiOutObject<>();

        biOut1.setFirst("Bob");
        biOut1.setSecond(30);

        biOut2.setFirst("Bob");
        biOut2.setSecond(30);

        assertEquals(biOut1, biOut2);
        assertEquals(biOut1.hashCode(), biOut2.hashCode());
    }

    @Test
    void testReset() {
        BiOutObject<String, Integer> biOut = new BiOutObject<>();
        biOut.setFirst("Charlie");
        biOut.setSecond(40);

        biOut.reset();

        assertNull(biOut.getFirst());
        assertNull(biOut.getSecond());
    }

    @Test
    void testApply(){
        BiOut<String, Integer> biOut = new BiOutObject<>();
        biOut.setFirst("Charlie");
        assertFalse(biOut.isApplied());

        biOut.setSecond(40);
        assertTrue(biOut.isApplied());

        biOut.reset();

        biOut.setSecond(40);
        assertFalse(biOut.isApplied());

        biOut.setFirst("Charlie");
        assertTrue(biOut.isApplied());
    }
}
