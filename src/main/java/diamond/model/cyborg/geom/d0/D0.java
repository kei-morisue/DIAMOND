/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public abstract class D0 implements Comparable<D0> {
    protected double x = .0;
    protected double y = .0;

    @Deprecated
    public D0() {
    }

    public D0(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    @Deprecated

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    @Deprecated

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
    }

    @Override
    public int compareTo(D0 d0) {
        if (Fuzzy.isSmall(Math.hypot(x, y))) {
            return 0;
        }
        if (x < d0.x) {
            return -1;
        }
        if (y < d0.y) {
            return -1;
        }
        return 1;
    }

}
