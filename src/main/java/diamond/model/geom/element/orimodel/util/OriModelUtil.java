/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel.util;

import java.util.ArrayList;
import java.util.List;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.orimodel.OriEdge;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriVertex;

/**
 * @author long_
 *
 */
public class OriModelUtil {
    public static boolean faceExistsOnLeft(OriVertex v, OriEdge e) {
        if (v == e.sv) {
            if (e.left != null) {
                return true;
            }
        } else {
            if (e.right != null) {
                return true;
            }
        }
        return false;
    }

    public static void makeHalfEdges(OriVertex v, OriEdge e, OriFace face) {
        OriVertex walkV = v;
        OriEdge walkE = e;
        int debugCount = 0;
        while (true) {
            if (debugCount++ > 200) {
                System.out.println("ERROR");
            }
            OriHalfEdge he = new OriHalfEdge(walkV, walkE, face);
            face.halfEdges.add(he);
            if (walkE.sv == walkV) {
                walkE.left = he;
            } else {
                walkE.right = he;
            }
            walkV = walkE.oppositeVertex(walkV);
            walkE = walkV.getPrevEdge(walkE);

            if (walkV == v) {
                break;
            }
        }
        face.makeHalfedgeLoop();
    }

    public static void makeEdges(List<OriEdge> edges, List<OriFace> faces) {
        edges.clear();

        ArrayList<OriHalfEdge> tmpHalfedges = new ArrayList<OriHalfEdge>();

        // Clear all the Halfedges
        for (OriFace face : faces) {
            for (OriHalfEdge he : face.halfEdges) {
                he.pair = null;
                he.edge = null;
                tmpHalfedges.add(he);
            }
        }

        // Search the halfedge pair
        int heNum = tmpHalfedges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfEdge he0 = tmpHalfedges.get(i);
            if (he0.pair != null) {
                continue;
            }

            for (int j = i + 1; j < heNum; j++) {
                OriHalfEdge he1 = tmpHalfedges.get(j);
                if (he0.vertex == he1.next.vertex
                        && he0.next.vertex == he1.vertex) {
                    OriEdge edge = new OriEdge();
                    he0.pair = he1;
                    he1.pair = he0;
                    he0.edge = edge;
                    he1.edge = edge;
                    edge.sv = he0.vertex;
                    edge.ev = he1.vertex;
                    edge.left = he0;
                    edge.right = he1;
                    edges.add(edge);
                    edge.type = LineType.AUX;//OriEdge.TYPE_NONE;
                }
            }
        }

        // If the pair wasnt found it should be an edge
        for (OriHalfEdge he : tmpHalfedges) {
            if (he.pair == null) {
                OriEdge edge = new OriEdge();
                he.edge = edge;
                edge.sv = he.vertex;
                edge.ev = he.next.vertex;
                edge.left = he;
                edges.add(edge);
                edge.type = LineType.CUT;
            }
        }
    }
}
