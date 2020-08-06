/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentMV;

/**
 * @author Kei Morisue
 *
 */
public class Face {
    private LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    private HashSet<SegmentMV> edges = new HashSet<SegmentMV>();
    private HashSet<SegmentCrease> creases = new HashSet<SegmentCrease>();
    private boolean isFront = false;
    private AffineTransform transform = null;

    public Face() {
    }

    public boolean isBoundary(Vertex v) {
        //TODO
        return false;
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    @Deprecated
    public void setVertices(LinkedList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public HashSet<SegmentCrease> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<SegmentCrease> creases) {
        this.creases = creases;
    }

    public boolean isFront() {
        return isFront;
    }

    public Face flip() {
        isFront = !isFront;
        return this;
    }

    @Deprecated
    public void setFront(boolean isFront) {
        this.isFront = isFront;
    }

    @Deprecated
    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public HashSet<SegmentMV> getEdges() {
        return edges;
    }
}
