/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.Point2DUtil;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class RotateArrowBody extends AbstractArrowBody {
    private static final double angle0 = Math.PI / 2;
    private static final double angle1 = -Math.PI / 2;
    private static final Double dir11 = new Double(1, 1);
    private static final Double dir10 = new Double(1, 0);

    @Override
    public double getAngle0(Double p0, Double p1) {
        return angle0;
    }

    @Override
    public double getAngle1(Double p0, Double p1) {
        return angle1;
    }

    @Override
    public void flip() {
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1) {
        g2d.setColor(OriArrowColor.ARROW_BODY);
        g2d.setStroke(LineStyle.STROKE_ARROW);
        Double o = Point2DUtil.center(p0, p1);
        double r = DistanceUtil.distance(p0, p1) / 4;
        Double corner0 = Point2DUtil.sub(o,
                Point2DUtil.scale(dir11, r));
        int x = (int) corner0.x;
        int y = (int) corner0.y;
        g2d.drawOval(x, y, (int) r * 2, (int) r * 2);
    }

    @Override
    public Double getP0(Double p0, Double p1) {
        double r = DistanceUtil.distance(p0, p1) / 4;
        Double o = Point2DUtil.center(p0, p1);
        return Point2DUtil.sub(o, Point2DUtil.scale(dir10, r));
    }

    @Override
    public Double getP1(Double p0, Double p1) {
        double r = DistanceUtil.distance(p0, p1) / 4;
        Double o = Point2DUtil.center(p0, p1);
        return Point2DUtil.plus(o, Point2DUtil.scale(dir10, r));
    }
}
