/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.ShapeBuilder;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class SegmentEdge extends AbstractSegment {
    private SegmentType type = SegmentType.VALLEY;
    private Face f0 = null;
    private Face f1 = null;

    @Deprecated
    public SegmentEdge() {
        super();
    }

    public SegmentEdge(Face f0, Vertex v0, Vertex v1) {
        super(v0, v1);
        this.setF0(f0);
    }

    @Override
    void split(Vertex v) {
        f0.add(v);//TODO
    }

    @Override
    public void draw(Graphics2D g2d, Diagram diagram) {
        g2d.draw(ShapeBuilder.build(this));

    }

    @Override
    public void setG2d(Graphics2D g2d, Diagram diagram) {
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getColor(this));
        g2d.setStroke(styleSegment.strokeEdge((float) G2DUtil.getScale(g2d)));
    }

    public Face getF1() {
        return f1;
    }

    public void setF1(Face f1) {
        this.f1 = f1;
    }

    public Face getF0() {
        return f0;
    }

    public void setF0(Face f0) {
        this.f0 = f0;
    }

    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        if (SegmentType.isCrease(type)) {
            return;
        }
        this.type = type;
    }

}
