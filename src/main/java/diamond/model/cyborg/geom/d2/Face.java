/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentEdge;
import diamond.model.cyborg.geom.m.MirrorSimple;
import diamond.model.cyborg.graphics.GraphicsCp;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Cyborg, GraphicsCp {
    private LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    private HashSet<SegmentEdge> edges = new HashSet<SegmentEdge>();
    private HashSet<SegmentCrease> creases = new HashSet<SegmentCrease>();
    private MirrorSimple mirror = null;

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

    @Override
    public void draw(Graphics2D g2d, Diagram diagram) {
        setG2d(g2d, diagram);
        for (SegmentCrease crease : creases) {
            crease.setG2d(g2d, diagram);
            crease.draw(g2d, diagram);
            Vertex v0 = crease.getV0();
            Vertex v1 = crease.getV1();
            v0.setG2d(g2d, diagram);
            v0.draw(g2d, diagram);
            v1.draw(g2d, diagram);
        }
        for (SegmentEdge edge : edges) {
            edge.setG2d(g2d, diagram);
            edge.draw(g2d, diagram);
        }
        for (Vertex v : vertices) {
            v.setG2d(g2d, diagram);
            v.draw(g2d, diagram);
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, Diagram diagram) {
        StyleFace styleFace = diagram.getStyleFace();
        StyleSegment styleSegment = diagram.getStyleSegment();
        double scale = G2DUtil.getScale(g2d);
        g2d.setColor(styleFace.getColor(false));
        g2d.setStroke(styleSegment.strokeEdge((float) scale));
        GeneralPath polygon = ShapeBuilder.build(this);
        g2d.fill(polygon);
        g2d.setColor(StyleSegment.COLOR_EDGE);
        g2d.draw(polygon);
    }

    public boolean isBoundary(Vertex v) {
        //TODO
        return false;
    }

    public void add(SegmentCrease crease) {
        creases.add(crease);
        crease.setFace(this);
    }

    public void add(SegmentEdge edge) {
        edges.add(edge);
    }

    public void remove(SegmentCrease crease) {
        creases.remove(crease);
    }

    public void remove(SegmentEdge edge) {
        edges.remove(edge);
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    public void add(Vertex v) {
        vertices.add(v);
    }

    @Deprecated
    public void setVertices(LinkedList<Vertex> vertices) {
        this.vertices = vertices;
    }

    // TODO to be deprocated
    public HashSet<SegmentCrease> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<SegmentCrease> creases) {
        this.creases = creases;
    }

    public boolean isFront() {
        return mirror.isFlip();
    }

    public HashSet<SegmentEdge> getEdges() {
        return edges;
    }

    public MirrorSimple getMirror() {
        return mirror;
    }

    public void setMirror(MirrorSimple mirror) {
        this.mirror = mirror;
    }

}
