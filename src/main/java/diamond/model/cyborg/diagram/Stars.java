/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import java.util.Collection;
import java.util.TreeSet;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class Stars {
    private TreeSet<Vertex> vertexs = new TreeSet<Vertex>();

    public TreeSet<Vertex> getVertexs() {
        return vertexs;
    }

    @Deprecated
    public void setVertexs(TreeSet<Vertex> vertexs) {
        this.vertexs = vertexs;
    }

    public void add(Vertex v) {
        vertexs.add(v);
    }

    public void addAll(Collection<Vertex> vertices) {
        vertexs.addAll(vertices);
    }

    public void pop(Vertex v) {
        vertexs.remove(v);
    }
}
