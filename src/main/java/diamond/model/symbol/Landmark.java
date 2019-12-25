/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Vertex;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Landmark extends Symbol<Vertex> {
    private Vertex vertex;
    private final static Color COLOR_EDGE = Color.black;
    private final static Color COLOR_BODY = Color.white;
    private static final double SIZE_LANDMARK_EDGE = 15.0;
    private static final double SIZE_LANDMARK_BODY = SIZE_LANDMARK_EDGE;
    private static final double STROKE_WIDTH_EDGE = SIZE_LANDMARK_EDGE * 0.5;
    private static final double STROKE_WIDTH_BODY = SIZE_LANDMARK_EDGE * 0.1;

    public Landmark() {
    }

    @Override
    public void drawCp(Graphics2D g2d) {
        drawAt(g2d, vertex);
    }

    @Override
    public void drawFolded(Graphics2D g2d) {
        drawAt(g2d, vertex.getFoldedOffset());
    }

    private void drawAt(Graphics2D g2d, Point2D.Double v) {
        double scale = G2DUtil.getScale(g2d);
        double size = (SIZE_LANDMARK_EDGE / scale);
        g2d.setColor(COLOR_EDGE);
        g2d.setStroke(new BasicStroke(
                (float) (STROKE_WIDTH_EDGE / scale),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        g2d.draw(new Ellipse2D.Double(v.x - size / 2, v.y - size / 2, size,
                size));
        g2d.setColor(COLOR_BODY);
        g2d.setStroke(new BasicStroke(
                (float) (STROKE_WIDTH_BODY / scale),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        size = (SIZE_LANDMARK_BODY / scale);
        g2d.draw(new Ellipse2D.Double(v.x - size / 2, v.y - size / 2, size,
                size));
    }

    @Override
    public void set(Vertex cyborg) {
        this.vertex = cyborg;
    }

    @Override
    public void flip(Cp cp) {
        cp.getSymbolsVertex().remove(vertex);
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
    public Vertex getVertex() {
        return vertex;
    }

    @Deprecated
    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

}
