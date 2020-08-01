/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math;

import diamond.model.Constants;

/**
 * @author Kei Morisue
 *
 */
public class Fuzzy {
    public static boolean in(double x, double a, double b) {
        return a - Constants.EPS < x && x < b + Constants.EPS;
    }

    public static boolean isSmall(double x) {
        return -Constants.EPS < x && x < Constants.EPS;
    }

}
