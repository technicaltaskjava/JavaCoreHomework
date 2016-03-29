package com.kokhanyuk.javase05;

/**
 * Tuples
 *
 * This class implements the generic data structure for storing multiple items of different types.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Tuples {

    public static <A> Unit<A> unit(A a) {
        return new Unit<A>(a);

    }

    public static <A, B> Pair<A, B> pair(A a, B b) {
        return new Pair<A, B>(a, b);

    }

    public static <A, B, C> Triplet<A, B, C> triplet(A a, B b, C c) {
        return new Triplet<A, B, C>(a, b, c);
    }

    public static class Unit<A> {
        final private A a;

        private Unit(A a) {
            this.a = a;
        }

        public A getFirst() {
            return a;
        }
    }

    public static class Pair<A, B> {
        final private A a;
        final private B b;

        private Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getFirst() {
            return a;
        }

        public B getSecond() {
            return b;
        }
    }

    public static class Triplet<A, B, C> {
        final private A a;
        final private B b;
        final private C c;

        private Triplet(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public A getFirst() {
            return a;
        }

        public B getSecond() {
            return b;
        }

        public C getThirt() {
            return c;
        }
    }
}
