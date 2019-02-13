/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.LineType;

/**
 * @author long_
 *
 */
public class OriHalfEdge {
    private OriHalfEdge next = null;
    private OriHalfEdge prev = null;
    private OriHalfEdge pair = null;

    private OriVertex sv = null;
    private OriVertex ev = null;

    private Point2D foldedSv = new Point2D.Double();
    private Point2D foldedEv = new Point2D.Double();

    private OriFace face = null;
    private LineType type = null;

    public OriHalfEdge(OriVertex sv, OriVertex ev, LineType type) {
        this.sv = sv;
        this.ev = ev;
        this.type = type;
    }

    public boolean remove() {
        return getSv().getHalfEdges().remove(this)
                && getEv().getHalfEdges().remove(getPair());
    }

    public double getAngle() {
        Vector2d dir = new Vector2d(ev.x - sv.x, ev.y - sv.y);
        return Math.atan2(dir.y, dir.x);
    }

    public boolean makePair(OriHalfEdge he) {
        this.pair = he;
        he.setPair(this);
        return true;
    }

    public OriHalfEdge getNext() {
        return this.next;
    }

    public void setNext(OriHalfEdge next) {
        this.next = next;
    }

    public OriHalfEdge getPrev() {
        return this.prev;
    }

    public void setPrev(OriHalfEdge prev) {
        this.prev = prev;
    }

    public OriHalfEdge getPair() {
        return this.pair;
    }

    private void setPair(OriHalfEdge pair) {
        this.pair = pair;
    }

    public OriFace getFace() {
        return this.face;
    }

    public void setFace(OriFace face) {
        this.face = face;
    }

    public LineType getType() {
        return this.type;
    }

    public OriVertex getSv() {
        return this.sv;
    }

    public OriVertex getEv() {
        return this.ev;
    }

    public Point2D getFoldedSv() {
        return this.foldedSv;
    }

    public Point2D getFoldedEv() {
        return this.foldedEv;
    }

}
