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
public class SegmentEdge extends SegmentBase {
    private Face f0;
    private Face f1;

    @Deprecated
    public SegmentEdge() {
        super();
    }

    public SegmentEdge(Face f0, Face f1, Vertex v0, Vertex v1) {
        super(v0, v1);
        this.type = SegmentType.VALLEY;
        this.f0 = f0;
        this.f1 = f1;
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getColor(type));
        g2d.setStroke(styleSegment.strokeCp((float) G2DUtil.getScale(g2d)));
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
        g2d.setColor(StyleSegment.COLOR_EDGE);
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setStroke(styleSegment.strokeEdge((float) G2DUtil.getScale(g2d)));
    }

    public SegmentEdge mirror(Mirror mirror) {
        SegmentEdge e = new SegmentEdge(
                f0,
                f1,
                mirror.apply(v0),
                mirror.apply(v1));
        e.setType(type);
        return e;
    }

    @Override
    public void split(Vertex v) {
        f0.add(v, v1, v0);
        f1.add(v, v1, v0);
    }

    public Face getPair(Face f) {
        if (f == f0) {
            return f1;
        }
        if (f == f1) {
            return f0;
        }
        return null;
    }

    public void setType(boolean isM) {
        this.type = (isM) ? SegmentType.MOUNTAIN : SegmentType.VALLEY;
    }

    @Deprecated
    public Face getF1() {
        return f1;
    }

    @Deprecated
    public void setF1(Face f1) {
        this.f1 = f1;
    }

    @Deprecated
    public Face getF0() {
        return f0;
    }

    @Deprecated
    public void setF0(Face f0) {
        this.f0 = f0;
    }

    public void setType(SegmentType type) {
        if (SegmentType.isCrease(type)) {
            this.type = SegmentType.foldUnfold(type);
        }
        this.type = type;
    }
}
