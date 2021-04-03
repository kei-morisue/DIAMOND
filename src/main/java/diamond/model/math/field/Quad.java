/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

import diamond.model.cyborg.Pair;

/**
 * @author Kei Morisue
 *
 */
public class Quad {
    // (-b + sqrt(b * b - 4 * a * c)) / 2 / a
    // avoiding digit loss ~ avoid subtract
    // -2 * c / (sqrt(b * b - 4 * a * c ) + abs(b))
    public static <T extends F<T>> T root0(T a, T b, T c) {
        if (a.isZero()) {
            if (!b.isZero()) {
                return c.div(b).neg();
            } else {
                return null;
            }
        }
        T ac4 = a.mul(4).mul(c);
        T bb = b.mul(b);
        T det = bb.sub(ac4);
        if (det.isNeg()) {
            return null;
        }
        if (b.isNeg()) {
            return b.neg().add(det.sqrt()).div(2).div(a);
        }
        return c.mul(2).div(b.add(det.sqrt()));
    }

    // c / a / root0
    public static <T extends F<T>> T root1(
            T root0,
            T a,
            T c) {
        if (a.isZero()) {
            return root0;
        }
        return c.div(a).div(root0);
    }

    public static boolean isSquared(long i) {
        long s = sqrt(i);
        return s * s == i;
    }

    public static long sqrt(long i) {
        return (long) Math.floor(Math.sqrt(i));
    }

    public static Pair<Long> pythagolian(long a) {
        for (long b = 1; b * b * 2 < a + 2; b++) {
            long cc = a - b * b;
            if (isSquared(cc)) {
                return new Pair<Long>(b, sqrt(cc));
            }
        }
        return null;
    }
}
