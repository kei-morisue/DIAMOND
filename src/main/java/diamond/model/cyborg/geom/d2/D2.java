/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class D2 {
    protected LinkedList<Vertex> vertices = new LinkedList<Vertex>();

    protected D2() {
    }

    public Vertex c() {
        double x = .0;
        double y = .0;
        for (Vertex v : vertices) {
            x += v.getX();
            y += v.getY();
        }
        int n = vertices.size();
        return new Vertex(x / n, y / n);
    }

    public boolean isBoundary(Vertex v) {
        //TODO
        return false;
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    @Deprecated
    public void add(Vertex v) {
        vertices.add(v);
    }

    public void add(Vertex v, Vertex v0, Vertex v1) {
        int i0 = vertices.indexOf(v0);
        int i1 = vertices.indexOf(v1);
        if (i0 == -1 || i1 == -1) {
            return;
        }
        vertices.add(Math.max(i0, i1), v);
    }

    @Deprecated
    public void setVertices(LinkedList<Vertex> vertices) {
        this.vertices = vertices;
    }

}
