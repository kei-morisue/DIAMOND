/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.m.Mirror;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class SegmentCrease extends AbstractSegment {
    private Face face;

    @Deprecated
    public SegmentCrease() {
    }

    public SegmentCrease(Vertex v0, Vertex v1, SegmentType type) {
        super(v0, v1);
        this.setType(type);
    }

    public static SegmentCrease mirror(
            AbstractSegment segment,
            Mirror mirror) {
        return new SegmentCrease(
                mirror.apply(segment.v0),
                mirror.apply(segment.v1),
                segment.type);
    }

    @Override
    public void split(Vertex v) {
        face.remove(this);
        face.add(new SegmentCrease(v0, v, type));
        face.add(new SegmentCrease(v, v1, type));
    }

    @Override
    public void draw(Graphics2D g2d, Diagram diagram) {
        if (type == SegmentType.CREASE) {
            StyleSegment styleSegment = diagram.getStyleSegment();
            double clipped0 = styleSegment.getClipped(face, v0);
            double clipped1 = styleSegment.getClipped(face, v1);
            g2d.draw(ShapeBuilder.build(this, clipped0, clipped1));
        } else {
            g2d.draw(ShapeBuilder.build(this));
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, Diagram diagram) {
        StyleSegment styleSegment = diagram.getStyleSegment();
        double scale = G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeCrease((float) scale, type));
        g2d.setColor(styleSegment.getColor(type));
    }

    public void setFace(Face face) {
        this.face = face;
    }

    @Deprecated
    public Face getFace() {
        return face;
    }
}
