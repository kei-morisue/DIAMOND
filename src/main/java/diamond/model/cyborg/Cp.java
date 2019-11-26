/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Kei Morisue
 *
 */
public class Cp {
    private LinkedList<Face> faces = new LinkedList<Face>();
    private Set<Vertex> vertices = new HashSet<Vertex>();
    private Set<HalfEdge> hes = new HashSet<HalfEdge>();

    public Cp() {
        CpBuilder.buildSquare(faces, vertices, hes);
    }

    public LinkedList<Face> getFaces() {
        return this.faces;
    }

    public void setFaces(LinkedList<Face> faces) {
        this.faces = faces;
    }

    public Set<Vertex> getVertices() {
        return this.vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Set<HalfEdge> getHalfEdges() {
        return this.hes;
    }

    public void setHes(Set<HalfEdge> hes) {
        this.hes = hes;
    }
}
