/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.Graphics;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.symbol.AbstractSymbol;
import diamond.view.ui.screen.TransformScreen;

/**
 * @author Kei Morisue
 *
 */
public class Step implements Graphics {
    private ArrayList<Face> faces = new ArrayList<>();
    private HashSet<AbstractSegment> segments = new HashSet<>();
    private HashSet<Vertex> vertices = new HashSet<>();
    private ArrayList<AbstractSymbol> symbols = new ArrayList<AbstractSymbol>();
    private TransformScreen transform = new TransformScreen();

    public Step() {
    }

    @Override
    public void draw(Graphics2D g2d, Diagram diagram) {
        for (Face face : faces) {
            face.draw(g2d, diagram);
        }
    }

    @Deprecated
    @Override
    public void setG2d(Graphics2D g2d, Diagram diagram) {
    }

    public void update() {
        setCyborg();
    }

    private void setCyborg() {
        setSegments();
        setVertices();
    }

    private void setSegments() {
        this.segments.clear();
        for (Face face : faces) {
            this.segments.addAll(face.getCreases());
            this.segments.addAll(face.getEdges());
        }
    }

    private void setVertices() {
        this.vertices.clear();
        for (AbstractSegment segment : segments) {
            vertices.add(segment.getV0());
            vertices.add(segment.getV1());
        }
        for (Face face : faces) {
            for (Vertex v : face.getVertices()) {
                vertices.add(v);
            }
        }

    }

    @Deprecated
    public TransformScreen getTransform() {
        return transform;
    }

    @Deprecated
    public void setTransform(TransformScreen transform) {
        this.transform = transform;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    @Deprecated
    public void setFaces(ArrayList<Face> faces) {
        this.faces = faces;
    }

    public ArrayList<AbstractSymbol> getSymbols() {
        return symbols;
    }

    @Deprecated

    public void setSymbols(ArrayList<AbstractSymbol> symbols) {
        this.symbols = symbols;
    }

    public HashSet<Vertex> getVertices() {
        return vertices;
    }

    public HashSet<AbstractSegment> getSegments() {
        return segments;
    }

}
