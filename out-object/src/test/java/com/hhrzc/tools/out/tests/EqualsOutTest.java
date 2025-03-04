package com.hhrzc.tools.out.tests;

import com.hhrzc.tools.out.impl.EqualsOutObjects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EqualsOutTest {
    @Test
    void testSetAndGet() {
        EqualsOutObjects<String> biOut = new EqualsOutObjects<>();
        biOut.setFirst("Alice");
        biOut.setSecond("25");

        assertEquals("Alice", biOut.getFirst());
        assertEquals("25", biOut.getSecond());
    }

    @Test
    void testEqualsAndHashCode() {
        EqualsOutObjects<String> biOut1 = new EqualsOutObjects<>();
        EqualsOutObjects<String> biOut2 = new EqualsOutObjects<>();

        biOut1.setFirst("Bob");
        biOut1.setSecond("30");

        biOut2.setFirst("Bob");
        biOut2.setSecond("30");

        assertEquals(biOut1, biOut2);
        assertEquals(biOut1.hashCode(), biOut2.hashCode());
    }

    @Test
    void testReset() {
        EqualsOutObjects<String> biOut = new EqualsOutObjects<>();
        biOut.setFirst("Charlie");
        biOut.setSecond("40");

        biOut.reset();

        assertNull(biOut.getFirst());
        assertNull(biOut.getSecond());
    }

    @Test
    void testApply(){
        EqualsOutObjects<String> biOut = new EqualsOutObjects<>();
        biOut.setFirst("Charlie");
        assertFalse(biOut.isApplied());

        biOut.setSecond("40");
        assertTrue(biOut.isApplied());

        biOut.reset();

        biOut.setSecond("40");
        assertFalse(biOut.isApplied());

        biOut.setFirst("Charlie");
        assertTrue(biOut.isApplied());
    }
}
