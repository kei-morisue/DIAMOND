/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleFace;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public final class Face extends FaceBase {
    public Face() {
        super();
    }

    @Override
    public double dist(Vertex v) {
        return c().dist(v);
    }

    @Override
    public void draw(Graphics2D g2d, ScreenMain screen) {
        setG2d(g2d, screen);
        GeneralPath polygon = ShapeBuilder.build(this);
        g2d.fill(polygon);
        for (SegmentCrease crease : creases) {
            crease.setG2d(g2d, screen);
            crease.draw(g2d, screen);
            Vertex v0 = crease.getV0();
            Vertex v1 = crease.getV1();
            v0.setG2d(g2d, screen);
            v0.draw(g2d, screen);
            v1.draw(g2d, screen);
        }
        for (Vertex v : vertices) {
            v.setG2d(g2d, screen);
            v.draw(g2d, screen);
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
        Diagram diagram = screen.diagram();
        StyleFace styleFace = diagram.getStyleFace();
        g2d.setColor(styleFace.getColor(false));
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
        setG2d(g2d, screen);
        GeneralPath polygon = ShapeBuilder.build(this, mirror);
        g2d.fill(polygon);
        for (SegmentCrease crease : creases) {
            SegmentCrease c = crease.mirror();
            c.setG2d(g2d, screen);
            c.draw(g2d, screen);
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
        Diagram diagram = screen.diagram();
        StyleFace styleFace = diagram.getStyleFace();
        g2d.setColor(styleFace.getColor(isFlip()));
    }

    @Override
    public Double clip() {
        double x0 = .0;
        double x1 = .0;
        double y0 = .0;
        double y1 = .0;
        for (Vertex v : vertices) {
            double x = v.getX();
            x0 = Math.min(x0, x);
            double y = v.getY();
            y0 = Math.min(y0, y);
            x1 = Math.max(x1, x);
            y1 = Math.max(y1, y);
        }
        double w = x1 - x0;
        double h = y1 - y0;
        Vertex c = c();
        double cx = c.getX();
        double cy = c.getY();
        return new Double(
                cx - 0.5 * w,
                cy - 0.5 * h,
                w,
                h);
    }

    public void add(SegmentCrease crease) {
        creases.add(crease);
        crease.setFace(this);
    }

}
