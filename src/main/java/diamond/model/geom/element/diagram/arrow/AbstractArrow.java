/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;
import diamond.model.geom.element.diagram.arrow.head.AbstractArrowHead;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public abstract class AbstractArrow {
    protected AbstractArrowBody body;
    protected AbstractArrowHead head0;
    protected AbstractArrowHead head1;
    private static final double scaleOriLine = 0.10;
    private double scale = 1.0;
    private Double offset = new Double(.0, .0);
    private boolean isSelected = false;

    protected AbstractArrow() {
        buildBody();
        buildHead0();
        buildHead1();
    }

    public void flip() {
        head0.flip();
        head1.flip();
        if (head0.isTail()) {
            body.flip();
        }
    }

    protected abstract void buildHead1();

    protected abstract void buildHead0();

    protected abstract void buildBody();

    public void draw(
            Graphics2D g2d,
            Double p0,
            Double p1) {
        body.draw(g2d, p0, p1, isSelected);
        head0.draw(g2d, p0, p1, isSelected);
        head1.draw(g2d, p0, p1, isSelected);
    };

    public void draw(Graphics2D g2d, OriLine oriLine) {
        OriPoint p0 = oriLine.p0;
        OriPoint p1 = oriLine.p1;
        draw(
                g2d,
                Point2DUtil.pivotScale(p0, p1, scaleOriLine),
                Point2DUtil.pivotScale(p1, p0, scaleOriLine));
    };

    public void draw(Graphics2D g2d, OriHalfEdge he) {
        Double p0 = he.getSv().getFoldedPosition();
        Double p1 = he.getEv().getFoldedPosition();
        g2d.translate(offset.x, offset.y);
        draw(g2d,
                Point2DUtil.pivotScale(p0, p1, scale),
                Point2DUtil.pivotScale(p1, p0, scale));
        g2d.translate(-offset.x, -offset.y);
    }

    @Deprecated
    public AbstractArrowBody getBody() {
        return this.body;
    }

    @Deprecated

    public void setBody(AbstractArrowBody body) {
        this.body = body;
    }

    @Deprecated

    public AbstractArrowHead getHead0() {
        return this.head0;
    }

    @Deprecated

    public void setHead0(AbstractArrowHead head0) {
        this.head0 = head0;
    }

    @Deprecated

    public AbstractArrowHead getHead1() {
        return this.head1;
    }

    @Deprecated

    public void setHead1(AbstractArrowHead head1) {
        this.head1 = head1;
    }

    @Deprecated
    public double getScale() {
        return this.scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    };

    public void selectUnselect() {
        isSelected = !isSelected;
    }

    public void unselect() {
        isSelected = false;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    @Deprecated
    public Double getOffset() {
        return this.offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }
}
