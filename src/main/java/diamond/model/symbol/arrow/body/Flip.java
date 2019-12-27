/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.body;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Flip extends AbstractArrowBody {
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
    public static final Color COLOR_BODY = Color.black;

    @Override
    public double getTailAngle(Double pT, Double pH) {
        return angle0;
    }

    @Override
    public double getHeadAngle(Double pT, Double pH) {
        return Point2DUtil.angle(Point2DUtil.sub(pH, pT)) - 0.125 * Math.PI;
    }

    @Override
    public Point2D.Double getTailPoint(Point2D.Double p0, Point2D.Double p1,
            double scale) {
        double r = p0.distance(p1) * 0.5 * scale;
        Double o = Point2DUtil.sub(CenterPointUtil.get(p0, p1),
                Point2DUtil.scale(dir01, r * 0.5));
        return Point2DUtil.add(o, Point2DUtil.scale(dir0, r));
    }

    @Override
    public Point2D.Double getHeadPoint(Point2D.Double p0, Point2D.Double p1,
            double scale) {
        double r = p0.distance(p1) * 0.5 * scale;
        Double o = Point2DUtil.sub(CenterPointUtil.get(p0, p1),
                Point2DUtil.scale(dir01, r * 0.5));
        return Point2DUtil.add(o, Point2DUtil.scale(dir1, r));
    }

    @Override
    public void flip() {
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            boolean isSelected) {
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_BODY);

        double w = tail.distance(head) * 0.5;
        double r1 = w * Math.sqrt(2.0);
        double r0 = r1 * 0.5;
        Double o1 = Point2DUtil.sub(CenterPointUtil.get(tail, head),
                Point2DUtil.scale(dir01, w));
        Double o0 = Point2DUtil.add(o1,
                Point2DUtil.scale(dir01, r0));

        double scale = G2DUtil.getScale(g2d);

        g2d.setStroke(new BasicStroke((float) (4.0 / scale),
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        Double corner0 = Point2DUtil.sub(o0,
                Point2DUtil.scale(dir11, r0));
        double x = corner0.x;
        double y = corner0.y;
        g2d.draw(new Ellipse2D.Double(x, y, r0 * 2, r0 * 2));
        Double corner1 = Point2DUtil.sub(o1,
                Point2DUtil.scale(dir11, r1));
        x = corner1.x;
        y = corner1.y;
        g2d.draw(new Arc2D.Double(x, y, r1 * 2, r1 * 2, theta0,
                theta1, Arc2D.Double.OPEN));
    }

}
