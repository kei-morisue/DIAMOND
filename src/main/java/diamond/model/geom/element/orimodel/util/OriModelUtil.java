/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel.util;

import java.util.ArrayList;
import java.util.List;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.orimodel.OriEdge;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriVertex;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.WARNING;

/**
 * @author long_
 *
 */
public class OriModelUtil {
    public static boolean faceExistsOnLeft(OriVertex v, OriEdge e) {
        if (e.isSv(v)) {
            if (e.getLeft() != null) {
                return true;
            }
        } else {
            if (e.getRight() != null) {
                return true;
            }
        }
        return false;
    }

    public static void setAdjacentHalfEdges(ArrayList<OriHalfEdge> halfEdges) {
        int heNum = halfEdges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfEdge he = halfEdges.get(i);
            he.setNext(halfEdges.get((i + 1) % heNum));
            he.setPrev(halfEdges.get((i - 1 + heNum) % heNum));
        }
    }

    public static OriFace makeOriFaceOnLeft(OriVertex v, OriEdge e) {
        OriVertex walkV = v;
        OriEdge walkE = e;
        OriFace face = new OriFace();
        int polygonNum = 0;
        while (true) {
            if (polygonNum++ > Constants.MAX_POLYGON) {
                System.out.println(
                        ResourceHolder.getWarningString(WARNING.WRONG_FACE));
                return null;
            }
            while (walkE.getType() == LineType.AUX) {
                face.auxLines.add(walkE);
                walkE = walkV.getPrevEdge(walkE);
            }
            face.addHalfEdge(walkV, walkE);

            walkV = walkE.oppositeVertex(walkV);
            walkE = walkV.getPrevEdge(walkE);

            if (walkV == v) {
                setAdjacentHalfEdges(face.halfEdges);
                face.setOutline(v, e);
                face.setPreviewOutline(v, e);
                return face;
            }
        }
    }

    public static void makePairsAndEdges(List<OriEdge> edges,
            List<OriFace> faces) {
        edges.clear();
        ArrayList<OriHalfEdge> tmpHalfedges = new ArrayList<OriHalfEdge>();
        for (OriFace face : faces) {
            for (OriHalfEdge he : face.halfEdges) {
                tmpHalfedges.add(he);
            }
        }
        // Search the halfedge pair
        int heNum = tmpHalfedges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfEdge he0 = tmpHalfedges.get(i);
            if (he0.getPair() != null) {
                continue;
            }
            for (int j = i + 1; j < heNum; j++) {
                OriHalfEdge he1 = tmpHalfedges.get(j);
                if (OriHalfEdge.makePair(he0, he1)) {
                    edges.add(OriHalfEdge.makeOriEdgeByPair(he0, he1));
                }
            }
        }
        // If the pair wasnt found it should be an edge
        for (OriHalfEdge he : tmpHalfedges) {
            if (he.getPair() == null) {
                edges.add(OriHalfEdge.makeOriEdgeBySingle(he));
            }
        }
    }
}
