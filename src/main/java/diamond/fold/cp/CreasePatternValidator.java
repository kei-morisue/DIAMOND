/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.fold.cp;

import java.util.List;

import javax.vecmath.Vector2d;

import diamond.fold.OriEdge;
import diamond.fold.OriFace;
import diamond.fold.OriHalfedge;
import diamond.fold.OriVertex;
import diamond.geom.GeomUtil;
import diamond.value.OriLine;

/**
 * @author long_
 *
 */
public class CreasePatternValidator {
    public static boolean checkProblems(List<OriVertex> vertices,
            List<OriFace> faces) {
        boolean isOk = true;
        for (OriFace face : faces) {
            face.hasProblem = !isConvex(face);
            isOk = isOk && !face.hasProblem;
        }
        for (OriVertex vertex : vertices) {
            vertex.hasProblem = !(isMaekawa(vertex) && isKawasaki(vertex));
            isOk = isOk && !vertex.hasProblem;
        }
        return isOk;
    }

    public static boolean isConvex(OriFace face) {
        if (face.halfedges.size() == 3) {
            return true;
        }

        OriHalfedge baseHe = face.halfedges.get(0);
        boolean baseFlg = GeomUtil.CCWcheck(baseHe.prev.vertex.p,
                baseHe.vertex.p, baseHe.next.vertex.p);

        for (int i = 1; i < face.halfedges.size(); i++) {
            OriHalfedge he = face.halfedges.get(i);
            if (GeomUtil.CCWcheck(he.prev.vertex.p, he.vertex.p,
                    he.next.vertex.p) != baseFlg) {
                return false;
            }

        }
        return true;
    }

    public static boolean isKawasaki(OriVertex v) {
        Vector2d p = v.p;
        double oddSum = 0;
        for (int i = 0; i < v.edges.size(); i++) {
            OriEdge e = v.edges.get(i);
            if (e.type == OriLine.TYPE_CUT) {
                return true;
            }

            Vector2d preP = new Vector2d(
                    v.edges.get(i).oppositeVertex(v).p);
            Vector2d nxtP = new Vector2d(v.edges
                    .get((i + 1) % v.edges.size()).oppositeVertex(v).p);

            nxtP.sub(p);
            preP.sub(p);

            if (i % 2 == 0) {
                oddSum += preP.angle(nxtP);
            } else {
            }
        }

        //System.out.println("oddSum = " + oddSum + "/ evenSum = " + evenSum);
        if (Math.abs(oddSum - Math.PI) > Math.PI / 180 / 2) {
            System.out.println("edge angle sum invalid");
            return false;
        }
        return true;
    }

    public static boolean isMaekawa(OriVertex v) {
        int ridgeCount = 0;
        int valleyCount = 0;
        for (OriEdge e : v.edges) {
            if (e.type == OriLine.TYPE_RIDGE) {
                ridgeCount++;
            } else if (e.type == OriLine.TYPE_VALLEY) {
                valleyCount++;
            } else if (e.type == OriLine.TYPE_CUT) {
                return true;
            }
        }

        if (Math.abs(ridgeCount - valleyCount) != 2) {
            System.out.println("edge type count invalid: " + v + " "
                    + Math.abs(ridgeCount - valleyCount));
            return false;
        }
        return true;
    }
}
