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
    private Set<HalfEdge> halfEdges = new HashSet<HalfEdge>();

    public Cp() {
        CpBuilder.buildSquare(faces);
    }

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
    public Set<Vertex> getVertices() {
        vertices.clear();
        for (Face face : faces) {
            for (HalfEdge he : face.getHalfEdges()) {
                vertices.add(he.getV0());
            }
        }
        return vertices;
    }

    @Deprecated
    public Set<HalfEdge> getHalfEdges() {
        halfEdges.clear();
        for (Face face : faces) {
            for (HalfEdge he : face.getHalfEdges()) {
                halfEdges.add(he);
            }
        }
        return halfEdges;
    }

}
