/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.step;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import diamond.model.cyborg.diagram.Stars;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.symbol.AbstractSymbol;

/**
 * @author Kei Morisue
 *
 */
public class Step {
    private ArrayList<Face> faces = new ArrayList<>();
    private ArrayList<AbstractSegment> segments = new ArrayList<>();
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<AbstractSymbol> symbols = new ArrayList<AbstractSymbol>();
    private AffineTransform transform = new AffineTransform();
    private Stars stars;

    @Deprecated
    public Step() {
    }

    public Step(Stars stars) {
        this.stars = stars;
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

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    @Deprecated
    public Stars getStars() {
        return stars;
    }

    @Deprecated
    public void setStars(Stars stars) {
        this.stars = stars;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<AbstractSegment> getSegments() {
        return segments;
    }

    public void update() {
        setSegments();
        setVertices();
    }

    private void setSegments() {
        ArrayList<AbstractSegment> candidates = new ArrayList<>();
        for (Face face : faces) {
            candidates.addAll(face.getCreases());
            candidates.addAll(face.getEdges());
        }
        this.segments = candidates;
    }

    private void setVertices() {
        ArrayList<Vertex> candidates = new ArrayList<>();
        for (Face face : faces) {
            candidates.addAll(face.getVertices());
        }
        this.vertices = candidates;
    }
}
