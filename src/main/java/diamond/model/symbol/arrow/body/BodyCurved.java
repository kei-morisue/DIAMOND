/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.body;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.util.Point2DUtil;
import diamond.view.ui.screen.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class BodyCurved extends AbstractArrowBody {
    boolean isClockwise = false;

    @Override
    public double getTailAngle(Double pT, Double pH) {
        Double p = Point2DUtil.sub(pT, getP2(pT, pH));
        return Point2DUtil.angle(p);
    }

    @Override
    public double getHeadAngle(Double pT, Double pH) {
        Double p = Point2DUtil.sub(pH,
                getP2(pT, pH));
        return Point2DUtil.angle(p);
    }

    @Override
    public void flip() {
        isLHS = !isLHS;
        if (isLHS) {
            isClockwise = !isClockwise;
        }
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            boolean isSelected) {
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_ARROW_BODY);
        g2d.setStroke(new BasicStroke((float) (3.0 / G2DUtil.getScale(g2d)),
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.draw(getCurve(tail, head));
    }

    private GeneralPath getCurve(Double tail, Double head) {
        GeneralPath path = new GeneralPath();
        Double p2 = getP2(tail, head);
        path.moveTo(tail.x, tail.y);
        path.curveTo(
                tail.x, tail.y,
                p2.x, p2.y,
                head.x, head.y);
        return path;
    }

    private Double getP2(Double p0, Double p1) {
        Point2D.Double dir = Point2DUtil.sub(p1, p0);
        double length = p0.distance(p1);
        double angle = Point2DUtil.angle(dir);

        Double middlePoint = Point2DUtil.sub(
                isLHS ? p1 : p0,
                Point2DUtil.scale(dir, (isLHS ? 1.0 : -1.0)));
        if (isClockwise) {
            angle -= Math.PI / 2;
        } else {
            angle += Math.PI / 2;
        }
        Point2D.Double norm = new Point2D.Double(length / 4 * Math.cos(angle),
                length / 4 * Math.sin(angle));
        middlePoint = Point2DUtil.add(middlePoint, norm);
        return middlePoint;
    }
}
