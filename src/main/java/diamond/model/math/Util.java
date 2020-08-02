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
    public static double window(double c, double a, double b) {
        return Math.max(a, Math.min(c, b));
    }

    public static int window(int c, int a, int b) {
        return Math.max(a, Math.min(c, b));
    }
}
