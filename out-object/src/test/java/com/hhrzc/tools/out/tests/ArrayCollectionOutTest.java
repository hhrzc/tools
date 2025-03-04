package com.hhrzc.tools.out.tests;
import com.hhrzc.tools.out.exceptions.ObjectIsNotInstantiatedException;
import com.hhrzc.tools.out.impl.ArrayListOut;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCollectionOutTest {
    @Test
    void testEqualsAndHashCode() {
        ArrayListOut<String> list1 = new ArrayListOut<>();
        ArrayListOut<String> list2 = new ArrayListOut<>();

        List<String> values = new ArrayList<>(Arrays.asList("A", "B", "C"));

        list1.set(values);
        list2.set(new ArrayList<>(values));

        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    void testAddAndRetrieve() {
        ArrayListOut<Integer> arrayListOut = new ArrayListOut<>();
        List<Integer> initialList = new ArrayList<>();
        arrayListOut.set(initialList);

        arrayListOut.add(1, 2, 3);

        assertEquals(Arrays.asList(1, 2, 3), arrayListOut.get());
    }

    @Test
    void testThrowsWhenNotInstantiated() {
        ArrayListOut<String> list = new ArrayListOut<>();

        Exception exception = assertThrows(ObjectIsNotInstantiatedException.class, () -> {
            list.add("A", "B");
        });

        assertEquals("List is not instantiated. Please use set() method to instantiate a list before using add().",
                exception.getMessage());
    }
}
