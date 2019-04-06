/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.awt.geom.AffineTransform;
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

    private OriFace face = null;
    private LineType type = null;

    public OriHalfEdge(OriVertex sv, OriVertex ev, LineType type) {
        this.sv = sv;
        this.ev = ev;
        this.type = type;
    }

    public double getAngle() {
        Vector2d dir = new Vector2d(ev.x - sv.x, ev.y - sv.y);
        return Math.atan2(dir.y, dir.x);
    }

    public void fold(AffineTransform transform) {
        sv.setFoldedPosition(new Point2D.Double());
        ev.setFoldedPosition(new Point2D.Double());
        transform.transform(sv, sv.getFoldedPosition());
        transform.transform(ev, ev.getFoldedPosition());
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

    public void setPair(OriHalfEdge pair) {
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

}
