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
    public OriVertex sv = null;
    public OriVertex ev = null;
    public OriHalfEdge left = null;
    public OriHalfEdge right = null;
    public LineType type = LineType.AUX;

    public OriEdge() {
    }

    public OriEdge(OriVertex sv, OriVertex ev, LineType type) {
        this.type = type;
        this.sv = sv;
        this.ev = ev;
    }

    public OriVertex oppositeVertex(OriVertex v) {
        if (v == sv) {
            return sv;
        }
        if (v == ev) {
            return ev;
        }
        return null;
    }
}
