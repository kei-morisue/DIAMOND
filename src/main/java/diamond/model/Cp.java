/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import diamond.Config;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class Cp {
    private LinkedList<Face> faces = new LinkedList<Face>();
    private Set<Vertex> vertices = new HashSet<Vertex>();
    private Set<HalfEdge> hes = new HashSet<HalfEdge>();

    public Cp() {
        double size = Config.PAPER_SIZE;
        Vertex v0 = new Vertex(size, size);
        Vertex v1 = new Vertex(-size, size);
        Vertex v2 = new Vertex(-size, -size);
        Vertex v3 = new Vertex(size, -size);
        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
        HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
        HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
        HalfEdge he3 = new HalfEdge(v3, v0, EdgeType.CUT);
        hes.add(he0);
        hes.add(he1);
        hes.add(he2);
        hes.add(he3);

        Face f0 = new Face();
        faces.add(f0);
        f0.open(he0);
        f0.add(he1);
        f0.add(he2);
        f0.close(he3);

        HalfEdge he0P = new HalfEdge(he0);
        HalfEdge he1P = new HalfEdge(he1);
        HalfEdge he2P = new HalfEdge(he2);
        HalfEdge he3P = new HalfEdge(he3);
        hes.add(he0P);
        hes.add(he1P);
        hes.add(he2P);
        hes.add(he3P);

        Face f1 = new Face();
        faces.add(f1);
        f1.open(he0P);
        f1.add(he1P);
        f1.add(he2P);
        f1.close(he3P);
        f1.getProperty().setDisabled(true);
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

    public Set<HalfEdge> getHes() {
        return this.hes;
    }

    public void setHes(Set<HalfEdge> hes) {
        this.hes = hes;
    }
}
