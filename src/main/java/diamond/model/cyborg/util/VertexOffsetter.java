/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.HashSet;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class VertexOffsetter {
    public static void offset(Cp cp, Vertex v) {
        HashSet<Vertex> selected = select(v, cp);
        for (Vertex vertex : selected) {
            vertex.setOffset(new Point2D.Double());
        }
    }

    public static void reset(Cp cp, Vertex v) {
        HashSet<Vertex> selected = select(v, cp);
    }

    private static HashSet<Vertex> select(Vertex v, Cp cp) {
        HashSet<Vertex> vertices = cp.getVertices();
        HashSet<Vertex> selected = new HashSet<Vertex>();
        for (Vertex v0 : vertices) {
            if (Fuzzy.around(v.getFolded().distance(v0), .0)) {
                selected.add(v0);
            }
        }
        return selected;
    }

}
