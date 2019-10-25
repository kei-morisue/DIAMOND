/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.util.Point2DUtil;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class RepeatArrowBody extends AbstractArrowBody {

    @Override
    public double getAngle0(Double p0, Double p1) {
        Double p = Point2DUtil.sub(getP0(p0, p1), getP1(p0, p1));
        return Point2DUtil.angle(p);
    }

    @Override
    public double getAngle1(Double p0, Double p1) {
        return .0;
    }

    @Override
    public void flip() {
        isLHS = !isLHS;
    }

    private GeneralPath getStroke(Double p0, Double p1) {
        GeneralPath path = new GeneralPath();
        path.moveTo(p0.x, p0.y);
        path.lineTo(p1.x, p1.y);
        return path;
    }

    @Override
    public Double getP0(Double p0, Double p1) {
        return Point2DUtil.center(p0, p1);
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        g2d.setColor(isSelected ? OriArrowColor.ARROW_SELECTED
                : OriArrowColor.ARROW_BODY);
        g2d.setStroke(LineStyle.STROKE_ARROW);
        g2d.draw(getStroke(getP0(p0, p1), getP1(p0, p1)));
        //TODO Add Cross line
    }

}
