/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.symbol.Symbol;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class Cp {
    private LinkedList<Face> faces = new LinkedList<Face>();
    private Face baseFace = null;
    private ScreenTransform transform = new ScreenTransform();
    private HashMap<Vertex, Symbol<Vertex>> symbolsVertex = new HashMap<Vertex, Symbol<Vertex>>();
    private HashMap<HalfEdge, Symbol<HalfEdge>> symbolsHalfEdge = new HashMap<HalfEdge, Symbol<HalfEdge>>();
    private HashMap<Face, Symbol<Face>> symbolsFace = new HashMap<Face, Symbol<Face>>();

    @Deprecated
    public Face getBaseFace() {
        return baseFace;
    }

    public Face buildBaseFace() {
        if (baseFace == null || !faces.contains(baseFace)) {
            return faces.get(0);
        }
        return baseFace;
    }

    public void setBaseFace(Face baseFace) {
        this.baseFace = baseFace;
    }

    public Cp() {
    }

    @Deprecated
    public LinkedList<Face> getFaces() {
        return this.faces;
    }

    @Deprecated
    public void setFaces(LinkedList<Face> faces) {
        this.faces = faces;
    }

    public void add(Face f) {
        faces.add(f);
    }

    public void remove(Face f) {
        faces.remove(f);
    }

    @Deprecated
    public HashSet<Vertex> getVertices() {
        HashSet<Vertex> vertices = new HashSet<Vertex>();
        for (Face face : faces) {
            for (HalfEdge he : face.getHalfEdges()) {
                vertices.add(he.getV0());
            }
            for (HalfEdge he : face.getUnsettledLines()) {
                vertices.add(he.getV0());
            }
        }
        return vertices;
    }

    @Deprecated
    public HashSet<HalfEdge> getHalfEdges() {
        HashSet<HalfEdge> halfEdges = new HashSet<HalfEdge>();
        for (Face face : faces) {
            for (HalfEdge he : face.getHalfEdges()) {
                halfEdges.add(he);
            }
            for (HalfEdge he : face.getUnsettledLines()) {
                halfEdges.add(he);
            }
        }
        return halfEdges;
    }

    @Deprecated
    public HashSet<HalfEdge> getUnsettlEdges() {
        HashSet<HalfEdge> halfEdges = new HashSet<HalfEdge>();
        for (Face face : faces) {
            for (HalfEdge he : face.getUnsettledLines()) {
                if (!he.getProperty().isDisabled()) {
                    halfEdges.add(he);
                }
            }
        }
        return halfEdges;
    }

    public ScreenTransform getTransform() {
        return transform;
    }

    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }

    public HashMap<Vertex, Symbol<Vertex>> getSymbolsVertex() {
        return symbolsVertex;
    }

    @Deprecated
    public void setSymbolsVertex(
            HashMap<Vertex, Symbol<Vertex>> symbolsVertex) {
        this.symbolsVertex = symbolsVertex;
    }

    public HashMap<HalfEdge, Symbol<HalfEdge>> getSymbolsHalfEdge() {
        return symbolsHalfEdge;
    }

    @Deprecated
    public void setSymbolsHalfEdge(
            HashMap<HalfEdge, Symbol<HalfEdge>> symbolsHalfEdge) {
        this.symbolsHalfEdge = symbolsHalfEdge;
    }

    public HashMap<Face, Symbol<Face>> getSymbolsFace() {
        return symbolsFace;
    }

    @Deprecated
    public void setSymbolsFace(HashMap<Face, Symbol<Face>> symbolsFace) {
        this.symbolsFace = symbolsFace;
    }

}
