/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public abstract class AbstractArrowBody {
    protected boolean isLHS = false;

    protected AbstractArrowBody() {

    }

    public abstract double getAngle0(Double p0, Double p1);

    public abstract double getAngle1(Double p0, Double p1);

    public Double getP1(Double p0, Double p1) {
        Double center = Point2DUtil.center(p0, p1);
        Double dir = Point2DUtil.rotate(
                Point2DUtil.scale(Point2DUtil.sub(p1, p0), 0.5), Math.PI / 2);
        return Point2DUtil.plus(center, dir);
    };

    public Double getP0(Double p0, Double p1) {
        Double center = Point2DUtil.center(p0, p1);
        Double dir = Point2DUtil.rotate(
                Point2DUtil.scale(Point2DUtil.sub(p0, p1), 0.5), Math.PI / 2);
        return Point2DUtil.plus(center, dir);
    };

    public abstract void flip();

    public abstract void draw(Graphics2D g2d, Double p0, Double p1);

    public boolean isLHS() {
        return this.isLHS;
    }

    @Deprecated
    public void setLHS(boolean isLHS) {
        this.isLHS = isLHS;
    }

}
