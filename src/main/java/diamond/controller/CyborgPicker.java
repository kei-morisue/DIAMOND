/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Stack;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class CyborgPicker {
    private Stack<Vertex> vertices = new Stack<>();
    private Stack<HalfEdge> halfEdges = new Stack<>();
    private Stack<Face> faces = new Stack<>();

    public CyborgPicker() {
    }

    public void clear() {
        for (Vertex v : vertices) {
            v.getProperty().isPicked = false;
        }
        for (HalfEdge he : halfEdges) {
            he.getProperty().isPicked = false;
        }
        for (Face face : faces) {
            face.getProperty().isPicked = false;
        }

        halfEdges.clear();
        vertices.clear();
        faces.clear();
    }

    public void popVertex() {
        if (vertices.isEmpty()) {
            return;
        } else {
            vertices.lastElement().getProperty().isPicked = false;
        }
        vertices.pop();
    }

    public void push(Vertex v) {
        vertices.push(v);
        v.getProperty().isPicked = true;
    }

    public void push(HalfEdge he) {
        halfEdges.push(he);
        he.getProperty().isPicked = true;
    }

    public void push(Face face) {
        faces.push(face);
        face.getProperty().isPicked = true;
    }

    public Stack<Vertex> getVertices() {
        return vertices;
    }

    public Stack<HalfEdge> getHalfEdges() {
        return halfEdges;
    }

    public Stack<Face> getFaces() {
        return faces;
    }

}
