/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

/**
 * @author Kei Morisue
 *
 */
public class Quad {
    // (-b + sqrt(b * b - 4 * a * c)) / 2 / a
    // avoiding digit loss ~ avoid subtract
    // 2 * c / (sqrt(b * b - 4 * a * c ) - abs(b))
    public static <T extends F<T>> F<T> root0(
            F<T> a,
            F<T> b,
            F<T> c) {
        F<T> ac4 = a.mul(4).mul(c);
        F<T> bb = b.mul(b);
        if (bb.sub(ac4).isNeg()) {
            return null;
        }
        return c.mul(2).div(sqaddsubsq(bb, ac4.neg(), b.abs()));
    }

    // c / a / root0
    public static <T extends F<T>> F<T> root1(
            F<T> root0,
            F<T> a,
            F<T> c) {
        return c.div(a).div(root0);
    }

    // srt(x + y) - sqrt(x)
    // avoiding digit loss ~ avoid subtract
    // y / ( sqrt(x  + y) + sqrt(x) )
    private static <T extends F<T>> F<T> sqaddsubsq(F<T> x, F<T> y,
            F<T> sqrtx) {
        return y.div(x.add(y).sqrt().add(sqrtx));
    }

    public static boolean isSquared(long i) {
        long s = sqrt(i);
        return s * s == i;
    }

    public static int sqrt(long i) {
        return (int) Math.floor(Math.sqrt(i));
    }
}
