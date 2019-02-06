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
public class OriHalfEdge {
    public OriHalfEdge next = null;
    public OriHalfEdge prev = null;
    public OriHalfEdge pair = null;
    public OriEdge edge = null;

    public OriVertex vertex = null;
    public OriFace face = null;
    public LineType type = null;

    public OriHalfEdge(OriVertex v, OriEdge e, OriFace f) {
        vertex = v;
        face = f;
        type = e.type;
    }
}
