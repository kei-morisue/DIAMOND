/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdge implements Cyborg {
    private Vertex v0;
    private Vertex v1;
    private EdgeType type;
    private HalfEdge next = null;
    private HalfEdge prev = null;
    private HalfEdge pair = null;
    private Face face = null;
    private HalfEdgeProperty property = new HalfEdgeProperty();
    private Symbol sign = null;

    public HalfEdge(Vertex v0, Vertex v1, EdgeType type) {
        this.v0 = v0;
        this.v1 = v1;
        this.type = type;
        v0.add(this);
    }

    public HalfEdge(HalfEdge he) {
        this.v0 = he.v1;
        this.v1 = he.v0;
        this.type = he.type;

    }

    public double dir() {
        return Math.atan2(v1.y - v0.y, v1.x - v0.x);
    }

    public HalfEdge getNext() {
        return next;
    }

    public void setNext(HalfEdge next) {
        this.next = next;
    }

    public HalfEdge getPrev() {
        return prev;
    }

    public void setPrev(HalfEdge prev) {
        this.prev = prev;
    }

    public HalfEdge getPair() {
        return pair;
    }

    public void setPair(HalfEdge pair) {
        this.pair = pair;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Vertex getV0() {
        return this.v0;
    }

    public void setV0(Vertex v0) {
        this.v0 = v0;
    }

    public Vertex getV1() {
        return this.v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public EdgeType getType() {
        return this.type;
    }

    public void setType(EdgeType type) {
        this.type = type;
    }

    public Symbol getSign() {
        return this.sign;
    }

    public void setSign(Symbol sign) {
        this.sign = sign;
    }

    public HalfEdgeProperty getProperty() {
        return property;
    }

    public void setProperty(HalfEdgeProperty property) {
        this.property = property;
    }
}
