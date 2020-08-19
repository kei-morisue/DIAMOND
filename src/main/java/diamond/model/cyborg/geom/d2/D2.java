/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.math.Fuzzy;
import diamond.model.math.Util;

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
        Vertex v0 = vertices.get(0);
        int size = vertices.size();
        for (int i = 1; i < size; ++i) {
            Direction d0 = v.dir(v0);
            Vertex v1 = vertices.get(i % size);
            Direction d1 = v1.dir(v0);
            if (Fuzzy.isSmall(d0.outer(d1)) && Util.in(d0.proj(d1), .0, 1.0)) {
                return true;
            }
            v0 = v1;
        }
        return false;
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    protected void add(Vertex v) {
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
