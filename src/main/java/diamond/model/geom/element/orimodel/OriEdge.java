/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import diamond.model.geom.element.LineType;

/**
 * @author long_
 *
 */
public class OriEdge {
    private OriVertex sv = null;
    private OriVertex ev = null;
    private OriHalfEdge left = null;
    private OriHalfEdge right = null;
    private LineType type = LineType.AUX;

    public OriEdge() {
    }

    public OriEdge(OriVertex sv, OriVertex ev, LineType type) {
        this.type = type;
        this.sv = sv;
        this.ev = ev;
    }

    public OriVertex oppositeVertex(OriVertex v) {
        if (isEv(v)) {
            return sv;
        }
        if (isSv(v)) {
            return ev;
        }
        return null;
    }

    public boolean isSv(OriVertex sv) {
        return this.sv == sv;
    }

    public boolean isEv(OriVertex ev) {
        return this.ev == ev;
    }

    public OriHalfEdge getLeft() {
        return this.left;
    }

    public void setLeft(OriHalfEdge left) {
        this.left = left;
    }

    public OriHalfEdge getRight() {
        return this.right;
    }

    public void setRight(OriHalfEdge right) {
        this.right = right;
    }

    public LineType getType() {
        return this.type;
    }

    @Deprecated
    public void setType(LineType type) {
        this.type = type;
    }

    public OriVertex getSv() {
        return this.sv;
    }

    public OriVertex getEv() {
        return this.ev;
    }
}
