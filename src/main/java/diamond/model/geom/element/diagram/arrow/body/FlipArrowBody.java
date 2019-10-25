/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.Point2DUtil;
import diamond.view.screen.draw.G2DUtil;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class FlipArrowBody extends AbstractArrowBody {
    private static final double angle0 = -3 * Math.PI / 4;
    private static final double angle1 = -Math.PI / 4;

    private static final int theta0 = -135;
    private static final int theta1 = 90;
    private static final Double dir0 = new Double(-Math.sqrt(2) / 2,
            Math.sqrt(2) / 2);
    private static final Double dir1 = new Double(Math.sqrt(2) / 2,
            Math.sqrt(2) / 2);
    private static final Double dir11 = new Double(1, 1);
    private static final Double dir01 = new Double(0, 1);

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
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        g2d.setColor(isSelected ? OriArrowColor.ARROW_SELECTED
                : OriArrowColor.ARROW_BODY);

        g2d.setStroke(LineStyle.STROKE_FLIP_ARROW);
        AffineTransform transform = g2d.getTransform();

        Double o = Point2DUtil.center(p0, p1);
        transform.transform(o, o);
        double scale = G2DUtil.getScale(g2d);
        g2d.setTransform(new AffineTransform());
        double r = DistanceUtil.distance(p0, p1) / 4 * scale;
        Double corner0 = Point2DUtil.sub(o,
                Point2DUtil.scale(dir11, r));
        int x = (int) corner0.x;
        int y = (int) corner0.y;
        g2d.drawOval(x, y, (int) (r * 2), (int) (r * 2));
        g2d.drawArc(x - (int) r, y - (int) (r * 2), (int) (r * 4),
                (int) (r * 4),
                theta0, theta1);
        g2d.setTransform(transform);
    }

    @Override
    public Double getP0(Double p0, Double p1) {
        double r = DistanceUtil.distance(p0, p1) / 2;
        Double o = Point2DUtil.sub(Point2DUtil.center(p0, p1),
                Point2DUtil.scale(dir01, r / 2));

        return Point2DUtil.plus(o, Point2DUtil.scale(dir0, r));
    }

    @Override
    public Double getP1(Double p0, Double p1) {
        double r = DistanceUtil.distance(p0, p1) / 2;
        Double o = Point2DUtil.sub(Point2DUtil.center(p0, p1),
                Point2DUtil.scale(dir01, r / 2));
        return Point2DUtil.plus(o, Point2DUtil.scale(dir1, r));
    }
}
