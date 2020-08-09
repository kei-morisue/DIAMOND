/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Collection;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.step.Step;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;
import diamond.model.cyborg.style.StyleVertex;

/**
 * @author Kei Morisue
 *
 */
public class CyborgDrawer {
    private StyleFace styleFace;
    private StyleSegment styleSegment;
    private float scale = 1.0f;

    public CyborgDrawer(Graphics2D g2d, Context context) {
        Diagram diagram = context.getDiagram();
        scale = (float) G2DUtil.getScale(g2d);
        this.styleFace = diagram.getStyleFace();
        this.styleSegment = diagram.getStyleSegment();
    }

    public void draw(Graphics2D g2d, Step step) {
        g2d.setStroke(styleSegment.strokeEdge(scale));
        for (Face face : step.getFaces()) {
            draw(g2d, face);
        }

    }

    public void draw(Graphics2D g2d, Collection<Vertex> vs) {
        g2d.setColor(StyleVertex.DEFAULT);
        for (Vertex v : vs) {
            draw(g2d, v);
        }
    }

    public void draw(Graphics2D g2d, Vertex v) {
        g2d.fill(ShapeBuilder.build(v, StyleVertex.SIZE / scale));
    }

    public void draw(Graphics2D g2d, Face face) {
        g2d.setColor(styleFace.getColor(face.isFront()));
        GeneralPath polygon = ShapeBuilder.build(face);
        g2d.fill(polygon);
        g2d.setStroke(styleSegment.strokeEdge(scale));
        g2d.setColor(StyleSegment.COLOR_EDGE);
        g2d.draw(polygon);

        for (SegmentCrease crease : face.getCreases()) {
            draw(g2d, face, crease);
        }
        draw(g2d, face.getVertices());
    }

    public void draw(Graphics2D g2d, Face face, SegmentCrease crease) {
        double clipped0 = styleSegment.getClipped(face, crease.getV0());
        double clipped1 = styleSegment.getClipped(face, crease.getV1());
        g2d.setStroke(styleSegment.strokeCrease(scale, crease.getType()));
        g2d.setColor(styleSegment.getColor(crease));
        g2d.draw(ShapeBuilder.build(crease, clipped0, clipped1));
    }

}
