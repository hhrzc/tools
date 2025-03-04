package com.hhrzc.tools.out.impl;

import com.hhrzc.tools.out.BiOut;

import java.util.Objects;

public class BiOutObject<T, K> implements BiOut<T, K> {
        private T first;
        private K second;

        @Override
        public void setFirst(T t) {
        this.first = t;
    }

        @Override
        public void setSecond(K k) {
        this.second = k;
    }

        @Override
        public T getFirst() {
        return first;
    }

        @Override
        public K getSecond() {
        return second;
    }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiOutObject<?, ?> biOut = (BiOutObject<?, ?>) o;
        return Objects.equals(first, biOut.first) &&
                Objects.equals(second, biOut.second);
    }

        @Override
        public int hashCode() {
        return Objects.hash(first, second);
    }

        @Override
        public String toString() {
        return "BiOutObject{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
