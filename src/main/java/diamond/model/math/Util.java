/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math;

/**
 * @author Kei Morisue
 *
 */
public class Util {

    public static double hairCut(double c, double a, double b) {
        return Math.max(a, Math.min(c, b));
    }

    public static int hairCut(int c, int a, int b) {
        return Math.max(a, Math.min(c, b));
    }

    public static boolean in(int c, int a, int b) {
        return a < c && c < b;
    }

    public static boolean in(double c, double a, double b) {
        if (Fuzzy.isSmall(c - a) || Fuzzy.isSmall(c - b)) {
            return true;
        }
        return a < c && c < b;
    }

}
