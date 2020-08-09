/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentMV;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Cyborg {
    private LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    private HashSet<SegmentMV> edges = new HashSet<SegmentMV>();
    private HashSet<SegmentCrease> creases = new HashSet<SegmentCrease>();
    private Mirror mirror = new MirrorLazy();

    public Face() {
    }

    public Vertex c() {
        double x = .0;
        double y = .0;
        for (Vertex v : vertices) {
            x += v.getX();
            y += v.getY();
        }
        int n = vertices.size();
        return new Vertex(x / n, y / n);
    }

    @Override
    public double dist(Vertex v) {
        return c().dist(v);
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
        return mirror.isFront();
    }

    public HashSet<SegmentMV> getEdges() {
        return edges;
    }

    @Deprecated
    public Mirror getMirror() {
        return mirror;
    }

    public void setMirror(Mirror mirror) {
        this.mirror = mirror;
    }
}
