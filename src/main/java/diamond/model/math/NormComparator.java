/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.math;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Comparator;

/**
 * @author Kei Morisue
 *
 */
public class NormComparator implements Comparator<Point2D.Double> {
    private Point2D.Double p;

    public NormComparator(Point2D.Double p) {
        this.p = p;
    }

    @Override
    public int compare(Double d1, Double d2) {
        return (p.distance(d1) < p.distance(d2)) ? -1 : 1;
    }
}
