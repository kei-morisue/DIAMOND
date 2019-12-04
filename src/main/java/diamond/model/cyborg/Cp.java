/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.fold.Folder;
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
    private HashSet<Symbol> symbols = new HashSet<Symbol>();

    public Face getBaseFace() {
        if (baseFace == null) {
            return faces.get(0);
        }
        return baseFace;
    }

    public void setBaseFace(Face baseFace) {
        this.baseFace = baseFace;
    }

    public Cp() {
        CpBuilder.buildSquare(faces);
        Folder.fold(this);
    }

    @Deprecated
    public LinkedList<Face> getFaces() {
        return this.faces;
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

    public ScreenTransform getTransform() {
        return transform;
    }

    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }

    public HashSet<Symbol> getSymbols() {
        return symbols;
    }

    @Deprecated
    public void setSymbols(HashSet<Symbol> symbols) {
        this.symbols = symbols;
    }

}
