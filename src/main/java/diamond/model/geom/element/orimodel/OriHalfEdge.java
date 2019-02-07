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
    private OriHalfEdge next = null;
    private OriHalfEdge prev = null;
    private OriHalfEdge pair = null;

    private OriVertex vertex = null;
    private OriFace face = null;
    private OriEdge edge = null;

    public OriHalfEdge(OriVertex v, OriEdge e, OriFace f) {
        this.vertex = v;
        this.face = f;
        this.edge = e;
        ;
    }

    public static boolean makePair(OriHalfEdge he0, OriHalfEdge he1) {
        if (he0.vertex != he1.next.vertex
                || he0.next.vertex != he1.vertex) {
            return false;
        }
        he0.pair = he1;
        he1.pair = he0;
        return true;
    }

    public static OriEdge makeOriEdgeByPair(OriHalfEdge he0, OriHalfEdge he1) {
        OriEdge edge = new OriEdge(
                he0.vertex,
                he1.vertex,
                LineType.AUX);
        he0.edge = edge;
        he1.edge = edge;
        edge.setLeft(he0);
        edge.setRight(he1);
        return edge;
    }

    public static OriEdge makeOriEdgeBySingle(OriHalfEdge he) {
        OriEdge edge = new OriEdge(
                he.vertex,
                he.next.vertex,
                LineType.CUT);
        he.edge = edge;
        edge.setLeft(he);
        return edge;
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

    public OriVertex getVertex() {
        return this.vertex;
    }

    public void setVertex(OriVertex vertex) {
        this.vertex = vertex;
    }

    public OriFace getFace() {
        return this.face;
    }

    public void setFace(OriFace face) {
        this.face = face;
    }

}
