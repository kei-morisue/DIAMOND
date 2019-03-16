/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.model.geom.element.cp;

import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriPoint extends Point2D.Double implements Comparable<OriPoint> {

    public OriPoint() {
        super();
    }

    public OriPoint(double x, double y) {
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

    public double distance(Vector2d v) {
        return Math.hypot(x - v.x, y - v.y);
    }

    public double angle(OriPoint origin) {
        return Math.atan2(this.y - y, this.x - x);
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public OriPoint sub(OriPoint p) {
        return new OriPoint(this.x - p.x, this.y - p.y);
    }

    public OriPoint add(OriPoint p) {
        return new OriPoint(this.x + p.x, this.y + p.y);
    }

    public OriPoint scale(double scale) {
        return new OriPoint(this.x * scale, this.y * scale);
    }

    public void normalize() {
        double length = Math.hypot(x, y);
        this.x /= length;
        this.y /= length;
    }
}
