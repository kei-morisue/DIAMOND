/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.math;

import diamond.Config;

/**
 * @author Kei Morisue
 *
 */
public class Fuzzy {
    private static double epsilon = Config.EPSILON;

    public static boolean between(double d, double w0, double w1) {
        return w0 - epsilon < d && d < w1 + epsilon;
    }

    public static boolean around(double d, double d1) {
        return d1 - epsilon < d && d < d1 + epsilon;
    }
}
