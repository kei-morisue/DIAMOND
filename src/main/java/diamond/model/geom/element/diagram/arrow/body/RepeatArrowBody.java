/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;
import java.util.Vector;

import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.util.Point2DUtil;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class RepeatArrowBody extends AbstractArrowBody {
    private Diagram d0;
    private Diagram d1;
    private Vector<Diagram> diagrams;

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

    @Deprecated
    public Diagram getD0() {
        return d0;
    }

    public void setD0(Diagram d0) {
        this.d0 = d0;
    }

    @Deprecated
    public Diagram getD1() {
        return d1;
    }

    public void setD1(Diagram d1) {
        this.d1 = d1;
    }

    @Deprecated
    public Vector<Diagram> getDiagrams() {
        return diagrams;
    }

    public void setDiagrams(Vector<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

}
