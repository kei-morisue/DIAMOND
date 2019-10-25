/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.util.Point2DUtil;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class CurvedArrowBody extends AbstractArrowBody {
    double scale;
    boolean isClockwise = false;

    public CurvedArrowBody(double scale) {
        this.scale = scale;

    }

    private GeneralPath getStroke(Double p0, Double p1) {
        GeneralPath path = new GeneralPath();
        Double p2 = getP2(p0, p1);
        path.moveTo(p0.x, p0.y);
        path.curveTo(
                p0.x, p0.y,
                p2.x, p2.y,
                p1.x, p1.y);
        return path;
    }

    private Double getP2(Double p0, Double p1) {
        Point2D.Double dir = Point2DUtil.sub(p1, p0);
        double length = Point2DUtil.diatance(p0, p1);
        double angle = Point2DUtil.angle(dir);

        Double middlePoint = Point2DUtil.sub(
                isLHS ? p1 : p0,
                Point2DUtil.scale(dir, this.scale * (isLHS ? 1.0 : -1.0)));
        if (isClockwise) {
            angle -= Math.PI / 2;
        } else {
            angle += Math.PI / 2;
        }
        Point2D.Double norm = Point2DUtil.build(length / 4, angle);
        middlePoint = Point2DUtil.plus(middlePoint, norm);
        return middlePoint;
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        g2d.setColor(isSelected ? OriArrowColor.ARROW_SELECTED
                : OriArrowColor.ARROW_BODY);
        g2d.setStroke(LineStyle.STROKE_ARROW);
        g2d.draw(getStroke(getP0(p0, p1), getP1(p0, p1)));
    }

    @Override
    public double getAngle0(Double p0, Double p1) {
        Double rotatedP0 = getP0(p0, p1);
        Double rotatedP1 = getP1(p0, p1);
        Double p = Point2DUtil.sub(rotatedP0,
                getP2(rotatedP0, rotatedP1));
        return Point2DUtil.angle(p);
    }

    @Override
    public double getAngle1(Double p0, Double p1) {
        Double rotatedP1 = getP1(p0, p1);
        Double rotatedP0 = getP0(p0, p1);
        Double p = Point2DUtil.sub(rotatedP1,
                getP2(rotatedP0, rotatedP1));
        return Point2DUtil.angle(p);
    }

    @Override
    public void flip() {
        isLHS = !isLHS;
        if (isLHS) {
            isClockwise = !isClockwise;
        }
    }

    @Deprecated
    public double getScale() {
        return this.scale;
    }

    @Deprecated

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Deprecated

    public boolean isClockwise() {
        return this.isClockwise;
    }

    @Deprecated

    public void setClockwise(boolean isClockwise) {
        this.isClockwise = isClockwise;
    }

}
