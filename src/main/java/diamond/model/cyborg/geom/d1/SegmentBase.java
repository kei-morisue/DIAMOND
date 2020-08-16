/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public abstract class SegmentBase extends D1 implements Cyborg {
    protected SegmentType type = SegmentType.CREASE;

    @Deprecated
    protected SegmentBase() {
    }

    @Override
    public double dist(Vertex v) {
        return v.dist(c());
    }

    @Override
    public Double clip() {
        Vertex c = c();
        double w = Math.abs(v0.getX() - v1.getX());
        double h = Math.abs(v0.getY() - v1.getY());
        return new Double(
                c.getX() - w * 0.5,
                c.getY() - h * 0.5,
                w,
                h);
    }

    @Override
    public void draw(Graphics2D g2d, ScreenMain screen) {
        g2d.draw(ShapeBuilder.build(this));
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
        g2d.draw(ShapeBuilder.build(this));
    }

    public SegmentBase(Vertex v0, Vertex v1) {
        super(v0, v1);
    }

    public SegmentBase(SegmentBase segment) {
        super(segment.v0, segment.v1);
        this.type = segment.type;
    }

    @Deprecated
    public SegmentType getType() {
        return type;
    }

}
