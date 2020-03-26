/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;

import diamond.model.cyborg.Step;
import diamond.model.cyborg.geom.Face;
import diamond.model.cyborg.geom.SegmentCrease;
import diamond.model.cyborg.geom.Vertex;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;

/**
 * @author Kei Morisue
 *
 */
public class Drawer {
    private StyleFace styleFace;
    private StyleSegment styleSegment;

    public Drawer(StyleFace styleFace, StyleSegment styleSegment) {
        this.styleFace = styleFace;
        this.styleSegment = styleSegment;
    }

    public void Draw(Graphics2D g2d, Step step) {
        double scale = G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeEdge(scale));
        for (Face face : step.getFaces()) {
            g2d.setColor(styleFace.getColor(face.isFront()));
            g2d.fill(ShapeBuilder.build(face));

            g2d.setStroke(styleSegment.strokeCrease(scale));
            for (SegmentCrease crease : face.getCreases()) {
                double clip0 = getClip(face, crease.getV0());
                double clip1 = getClip(face, crease.getV1());
                g2d.draw(ShapeBuilder.build(crease, clip0, clip1));
            }
        }

    }

    private double getClip(Face face, Vertex v1) {
        double clip1 = (face.isBoundary(v1))
                ? styleSegment.getClip()
                : .0;
        return clip1;
    }
}
