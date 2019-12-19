/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class CyborgPointer {
    private Vertex vertex = null;
    private HalfEdge halfEdge = null;
    private Face face = null;

    public CyborgPointer() {
    }

    public void clear() {
        setVertex(null);
        setHalfEdge(null);
        setFace(null);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Face getFace() {
        return face;
    }

    public HalfEdge getHalfEdge() {
        return halfEdge;
    }

    public void setHalfEdge(HalfEdge he) {
        if (this.halfEdge != null) {
            this.halfEdge.getProperty().isPointed = false;
        }
        this.halfEdge = he;
        if (this.halfEdge != null) {
            he.getProperty().isPointed = true;
        }
    }

    public void setVertex(Vertex vertex) {
        if (this.vertex != null) {
            this.vertex.getProperty().isPointed = false;
        }
        this.vertex = vertex;
        if (this.vertex != null) {
            vertex.getProperty().isPointed = true;
        }
    }

    public void setFace(Face face) {
        if (this.face != null) {
            this.face.getProperty().isPointed = false;
        }
        this.face = face;
        if (this.face != null) {
            face.getProperty().isPointed = true;
        }
    }

}
