/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.model.symbol.Symbol;
import diamond.model.symbol.arrow.body.AbstractArrowBody;
import diamond.model.symbol.arrow.head.AbstractArrowHead;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractArrow extends Symbol<HalfEdge> {
    protected AbstractArrowHead arrowHead;
    protected AbstractArrowHead arrowTail;
    protected AbstractArrowBody body;
    protected HalfEdge halfEdge;
    protected double scale = 1.0;
    protected Point2D.Double offset = new Point2D.Double(.0, .0);
    protected static final double scaleCp = 0.05;

    protected boolean isSelected = false;

    public AbstractArrow() {
        buildBody();
        buildTail();
        arrowTail.setTail(true);
        buildHead();
    }

    protected abstract void buildHead();

    protected abstract void buildTail();

    protected abstract void buildBody();

    @Override
    public void drawCp(Graphics2D g2d) {
        Vertex v0 = halfEdge.getV0();
        Vertex v1 = halfEdge.getV1();
        Double tail = body.getTailPoint(v0, v1, scale * scaleCp);
        Double head = body.getHeadPoint(v0, v1, scale * scaleCp);
        draw(g2d, tail, head);
    }

    @Override
    public void drawFolded(Graphics2D g2d) {
        Double v0 = halfEdge.getV0().getFoldedOffset();
        Double v1 = halfEdge.getV1().getFoldedOffset();
        Double tail = body.getTailPoint(v0, v1, scale);
        Double head = body.getHeadPoint(v0, v1, scale);
        Double t = Point2DUtil.add(tail, offset);
        Double h = Point2DUtil.add(head, offset);
        draw(g2d, t, h);
    }

    private void draw(Graphics2D g2d, Double tail, Double head) {
        body.draw(g2d, tail, head, isSelected);
        arrowHead.draw(g2d, tail, head, body, isSelected);
        arrowTail.draw(g2d, tail, head, body, isSelected);
    }

    @Override
    public void flip(Cp cp) {
        arrowHead.flip();
        arrowTail.flip();
        if (arrowHead.isTail()) {
            body.flip();
        }
    }

    @Override
    public void set(HalfEdge halfEdge) {
        this.halfEdge = halfEdge;
    }

    public AbstractArrowHead getHead0() {
        return arrowHead;
    }

    public void setHead0(AbstractArrowHead head0) {
        this.arrowHead = head0;
    }

    public AbstractArrowHead getHead1() {
        return arrowTail;
    }

    public void setHead1(AbstractArrowHead head1) {
        this.arrowTail = head1;
    }

    public HalfEdge getHalfEdge() {
        return halfEdge;
    }

    public void setHalfEdge(HalfEdge halfEdge) {
        this.halfEdge = halfEdge;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Point2D.Double getOffset() {
        return offset;
    }

    @Override
    public void setOffset(Point2D.Double offset) {
        this.offset = offset;
    }

    public AbstractArrowBody getBody() {
        return body;
    }

    public void setBody(AbstractArrowBody body) {
        this.body = body;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
