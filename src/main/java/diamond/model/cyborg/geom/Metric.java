/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

/**
 * @author Kei Morisue
 *
 */
public interface Metric {
    public boolean isNear(double x, double y, double scale);

    public double distSquare(double x, double y);
}
