/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.HalfEdge;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class AuxSegment extends Symbol<HalfEdge> {
    private HalfEdge halfEdge;
    private final static Color COLOR = Color.MAGENTA;
    private static final double WIDTH = 3.0;

    public AuxSegment() {
    }

    @Override
    public void drawCp(Graphics2D g2d) {
        drawAt(g2d, halfEdge.getV0(), halfEdge.getV1());
    }

    @Override
    public void drawFolded(Graphics2D g2d) {
        drawAt(
                g2d,
                halfEdge.getV0().getFoldedOffset(),
                halfEdge.getV1().getFoldedOffset());
    }

    private void drawAt(
            Graphics2D g2d,
            Point2D.Double v0,
            Point2D.Double v1) {
        double scale = G2DUtil.getScale(g2d);
        g2d.setColor(COLOR);
        g2d.setStroke(new BasicStroke(
                (float) (WIDTH / scale),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        g2d.draw(new Line2D.Double(v0, v1));
    }

    @Override
    public void set(HalfEdge cyborg) {
        this.halfEdge = cyborg;
    }

    @Override
    public void flip(Cp cp) {
        cp.getSymbolsHalfEdge().remove(halfEdge);
    }

    @Override
    public void setOffset(Double p) {
    }

    @Override
    public void setSelected(boolean isSelected) {
    }

    @Override
    public void setScale(double scale) {
    }

    @Override
    public java.awt.geom.Rectangle2D.Double clip() {
        return null;
    }

    @Deprecated
    public HalfEdge getVertex() {
        return halfEdge;
    }

    @Deprecated
    public void setVertex(HalfEdge halfEdge) {
        this.halfEdge = halfEdge;
    }

}
