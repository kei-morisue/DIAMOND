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
public class FlipArrowBody extends AbstractArrowBody {
    private static final double angle1 = -Math.PI / 4;
    private static final int r0 = 50;
    private static final int x = -r0;
    private static final int y = -r0;
    private static final int r1 = r0 << 1;
    private static final int theta0 = -135;
    private static final int theta = 90;
    private static final Double p0 = new Double(
            x + r0 + r1 * Math.cos(Math.toRadians(-theta0)),
            y + r1 * Math.sin(Math.toRadians(-theta0)));
    private static final Double p1 = new Double(
            x + r0 + r1 * Math.cos(Math.toRadians(-theta0 - theta)),
            y + r1 * Math.sin(Math.toRadians(-theta0 - theta)));

    @Override
    public double getAngle0(Double p0, Double p1) {
        return 0;
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
        g2d.drawOval(x, y, r0 << 1, r0 << 1);
        g2d.drawArc(x - r0, y - r1, r1 << 1, r1 << 1, theta0, theta);
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
