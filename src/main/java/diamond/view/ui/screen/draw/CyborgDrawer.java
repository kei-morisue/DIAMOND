/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import diamond.controller.Context;
import diamond.model.cyborg.geom.Face;
import diamond.model.cyborg.geom.SegmentCrease;
import diamond.model.cyborg.geom.Vertex;
import diamond.model.cyborg.step.Step;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;

/**
 * @author Kei Morisue
 *
 */
public class CyborgDrawer {
    private StyleFace styleFace;
    private StyleSegment styleSegment;
    private double scale = 1.0;
    private double clipped = 1.0;

    public CyborgDrawer(Context context) {
        this.styleFace = context.getStyleFace();
        this.styleSegment = context.getStyleSegment();
    }

    public void draw(Graphics2D g2d, Step step) {
        scale = G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeEdge(scale));
        for (Face face : step.getFaces()) {
            draw(g2d, face);
        }

    }

    private void draw(Graphics2D g2d, Face face) {
        g2d.setColor(styleFace.getColor(face.isFront()));
        GeneralPath polygon = ShapeBuilder.build(face);
        g2d.fill(polygon);
        g2d.setStroke(styleSegment.strokeEdge(scale));
        g2d.setColor(StyleSegment.COLOR_EDGE);
        g2d.draw(polygon);

        g2d.setStroke(styleSegment.strokeCrease(scale));
        g2d.setColor(StyleSegment.COLOR_CREASE);
        clipped = 1.0 - styleSegment.getClip();
        for (SegmentCrease crease : face.getCreases()) {
            draw(g2d, face, crease);
        }
    }

    private void draw(Graphics2D g2d, Face face, SegmentCrease crease) {
        double clipped0 = getClipped(face, crease.getV0());
        double clipped1 = getClipped(face, crease.getV1());
        g2d.draw(ShapeBuilder.build(crease, clipped0, clipped1));
    }

    private double getClipped(Face face, Vertex v1) {
        return (face.isBoundary(v1)) ? clipped : 1.0;
    }
}
