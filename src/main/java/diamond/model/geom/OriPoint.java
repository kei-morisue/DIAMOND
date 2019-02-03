/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.model.geom;

import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriPoint extends Vector2d implements Comparable<OriPoint> {
    public OriPoint(Double x, Double y) {
        super(x, y);
    }

    public OriPoint(Point2D p) {
        super(p.getX(), p.getY());
    }

    @Override
    public int compareTo(OriPoint o) {
        if (this.x == o.x) {
            return (int) Math.signum(this.y - o.y);
        }
        return (int) Math.signum(this.x - o.x);
    }

}
