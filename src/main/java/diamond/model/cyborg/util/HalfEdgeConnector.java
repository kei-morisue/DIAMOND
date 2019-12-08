/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;
import java.util.List;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeConnector {
    public static List<HalfEdge> connect(Face face, List<Vertex> vertexs,
            EdgeType type) {
        ArrayList<HalfEdge> hes = new ArrayList<HalfEdge>();
        Vertex v0 = vertexs.get(0);
        for (Vertex v1 : vertexs) {
            if (v0 == v1) {
                v0 = v1;
                continue;
            }
            HalfEdge halfEdge = getHalfEdge(v0, v1);
            if (halfEdge == null) {
                HalfEdge he = new HalfEdge(v0, v1, type);
                hes.add(he);
                face.add(he);
            } else {
                hes.add(halfEdge);
            }
            v0 = v1;
        }
        return hes;
    }

    private static HalfEdge getHalfEdge(Vertex v0, Vertex v1) {
        for (HalfEdge he : v1.getHalfEdges()) {
            if (he.getV1() == v0) {
                return he.getPair();
            }
        }
        return null;
    }
}
