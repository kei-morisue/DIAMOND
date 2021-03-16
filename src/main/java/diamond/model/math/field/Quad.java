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
    /*    public static <T extends F<T>> F<T> root(
            F<T> a0,
            F<T> a1,
            F<T> a2) {
    
    }*/

    public static boolean isSquared(int i) {
        int s = sqrt(i);
        return s * s == i;
    }

    public static int sqrt(int i) {
        return (int) Math.floor(Math.sqrt(i));
    }
}
