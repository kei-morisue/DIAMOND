/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.view.ui.screen.draw.FaceDrawer;

/**
 * @author Kei Morisue
 *
 */
public class CyborgPointer {
    private Vertex vertex = null;
    private HalfEdge he = null;
    private FaceDrawer face = null;

    public CyborgPointer() {
    }

    public void clear() {
        setVertex(null);
        setHe(null);
        setFace(null);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public FaceDrawer getFace() {
        return face;
    }

    public HalfEdge getHe() {
        return he;
    }

    public void setHe(HalfEdge he) {
        this.he = he;
    }

    public void setVertex(Vertex vertex) {
        if (this.vertex != null) {
            vertex.getProperty().isPointed = false;
        }
        this.vertex = vertex;
        if (this.vertex != null) {
            vertex.getProperty().isPointed = true;
        }
    }

    public void setFace(FaceDrawer face) {
        this.face = face;
    }

}
