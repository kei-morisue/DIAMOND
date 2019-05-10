/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class RotateArrowBody extends AbstractArrowBody {
    private static final double angle0 = Math.PI / 2;
    private static final double angle1 = -Math.PI / 2;
    private static final int r = 50;
    private static final int x = -r;
    private static final int y = -r;
    private static final Double p0 = new Double(x + (r << 1), y + r);
    private static final Double p1 = new Double(x, y + r);

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
        g2d.drawOval(x, y, r << 1, r << 1);
    }

    @Override
    public Double getP0(Double p0, Double p1) {
        return this.p0;
    }

    @Override
    public Double getP1(Double p0, Double p1) {
        return this.p1;
    }
}
