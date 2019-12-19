/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.body;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Rotate extends AbstractArrowBody {
    private static final double angle0 = Math.PI / 2;
    private static final double angle1 = -Math.PI / 2;
    private static final Double dir11 = new Double(1, 1);
    private static final Double dir10 = new Double(1, 0);
    public static final Color COLOR_BODY = Color.black;

    @Override
    public double getTailAngle(Double pT, Double pH) {
        return angle0;
    }

    @Override
    public double getHeadAngle(Double pT, Double pH) {
        return angle1;
    }

    @Override
    public void flip() {
    }

    @Override
    public Double getTailPoint(Double p0, Double p1, double scale) {
        double r = p0.distance(p1) / 4 * scale;
        Double o = CenterPointUtil.get(p0, p1);
        return Point2DUtil.sub(o, Point2DUtil.scale(dir10, r));
    }

    @Override
    public Double getHeadPoint(Double p0, Double p1, double scale) {
        double r = p0.distance(p1) / 4 * scale;
        Double o = CenterPointUtil.get(p0, p1);
        return Point2DUtil.add(o, Point2DUtil.scale(dir10, r));
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            boolean isSelected) {
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_BODY);
        g2d.setStroke(new BasicStroke((float) (3.0 / G2DUtil.getScale(g2d)),
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        Double o = CenterPointUtil.get(tail, head);
        double r = tail.distance(head) / 2;
        Double corner0 = Point2DUtil.sub(o,
                Point2DUtil.scale(dir11, r));
        double x = corner0.x;
        double y = corner0.y;
        g2d.draw(new Ellipse2D.Double(x, y, r * 2, r * 2));
    }

}
