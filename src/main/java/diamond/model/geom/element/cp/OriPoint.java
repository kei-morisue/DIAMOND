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
    private Double offset = new Double(.0, .0);
    private boolean isLandmark = false;

    public OriPoint() {
        super();
        isLandmark = false;
    }

    @Deprecated
    public void setX(double x) {
        this.x = x;
    }

    @Deprecated
    public void setY(double y) {
        this.y = y;
    }

    public OriPoint(double x, double y) {
        super(x, y);
    }

    public OriPoint(Point2D p) {
        super(p.getX(), p.getY());
    }

    public OriPoint(OriPoint p) {
        super(p.x, p.y);
        this.offset = p.offset;
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

    public double angle(OriPoint p) {
        return Math.atan2(y, x) - Math.atan2(p.y, p.x);
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public OriPoint sub(OriPoint p) {
        return new OriPoint(this.x - p.x, this.y - p.y);
    }

    public OriPoint negate() {
        return new OriPoint(-this.x, -this.y);
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

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public void setOffset(double x, double y) {
        this.offset.x = x;
        this.offset.y = y;
    }

    public boolean isLandmark() {
        return this.isLandmark;
    }

    public void flipLandmark() {
        isLandmark = !isLandmark;
    }

    public void disableLandmark() {
        isLandmark = false;
    }

    @Deprecated
    public void setLandmark(boolean isLandmark) {
        this.isLandmark = isLandmark;
    }
}
