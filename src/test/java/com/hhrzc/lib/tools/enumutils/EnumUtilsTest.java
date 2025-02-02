package com.hhrzc.lib.tools.enumutils;

import com.hrzc.lib.tools.utils.EnumUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

class EnumUtilsTest {
    @Test
    void testRandom() {
        enum E {
            A, B, C, D, E
        }
        E[] es = new E[5];
        for (int i = 0; i < 5; i++) {
            E randomEnum = EnumUtils.getRandomEnum(E.class);
            Assertions.assertNotNull(randomEnum, "Random enum should not be null");
            Assertions.assertTrue(EnumSet.allOf(E.class).contains(randomEnum), "Random enum should be a valid enum constant");
            es[i] = randomEnum;
            System.out.println(es[i]);
        }
        Set<E> uniqueEnums = new HashSet<>(Arrays.asList(es));
        Assertions.assertTrue(uniqueEnums.size() >= 2, "At least 2 different values has to be in the result set");
    }
}
