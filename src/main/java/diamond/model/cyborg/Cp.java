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
    private Set<HalfEdge> halfEdfes = new HashSet<HalfEdge>();

    public Cp() {
        CpBuilder.buildSquare(faces, vertices, halfEdfes);
    }

    public LinkedList<Face> getFaces() {
        return this.faces;
    }

    public void add(Vertex v) {
        vertices.add(v);
    }

    public void add(Face f) {
        faces.add(f);
    }

    public void remove(Face f) {
        faces.remove(f);
    }

    public void add(HalfEdge he) {
        halfEdfes.add(he);
        halfEdfes.add(he.getPair());
    }

    @Deprecated
    public void setFaces(LinkedList<Face> faces) {
        this.faces = faces;
    }

    public Set<Vertex> getVertices() {
        return this.vertices;
    }

    @Deprecated
    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Set<HalfEdge> getHalfEdges() {
        return this.halfEdfes;
    }

    @Deprecated
    public void setHes(Set<HalfEdge> hes) {
        this.halfEdfes = hes;
    }
}
