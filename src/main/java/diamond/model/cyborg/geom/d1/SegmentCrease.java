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
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class SegmentCrease extends SegmentBase {
    private Face face;

    @Deprecated
    public SegmentCrease() {
    }

    public SegmentCrease(SegmentBase segment) {
        super(segment.v0, segment.v1);
        this.setType(segment.type);
    }

    public SegmentCrease(Vertex v0, Vertex v1, SegmentType type) {
        super(v0, v1);
        this.setType(type);
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getColor(type));
        float scale = (float) G2DUtil.getScale(g2d);
        g2d.setStroke(
                styleSegment.strokeCrease(scale, type));
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getColor(type));
        float scale = (float) G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeCrease(scale, type));
    }

    public SegmentCrease mirror(Mirror mirror) {
        return new SegmentCrease(
                mirror.apply(v0),
                mirror.apply(v1),
                type);
    }

    @Override
    public void split(Vertex v) {
        face.remove(this);
        face.add(new SegmentCrease(v0, v, type));
        face.add(new SegmentCrease(v, v1, type));
    }

    public void setFace(Face face) {
        this.face = face;
    }

    @Deprecated
    public Face getFace() {
        return face;
    }

    public void setType(SegmentType type) {
        if (!SegmentType.isCrease(type)) {
            this.type = SegmentType.foldUnfold(type);
        }
        this.type = type;
    }
}
