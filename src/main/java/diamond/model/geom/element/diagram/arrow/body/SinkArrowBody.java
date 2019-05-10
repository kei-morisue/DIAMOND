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
public class SinkArrowBody extends AbstractArrowBody {

    @Override
    public double getAngle0(Double p0, Double p1) {
        return Point2DUtil.angle(Point2DUtil.sub(getP1(p0, p1), getP0(p0, p1)));
    }

    @Override
    public double getAngle1(Double p0, Double p1) {
        return Point2DUtil.angle(Point2DUtil.sub(getP0(p0, p1), getP1(p0, p1)));
    }

    @Override
    public void flip() {
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1) {

    }
}
