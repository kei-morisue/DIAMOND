/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.util.List;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.Line;
import diamond.model.geom.element.orimodel.OriEdge;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.geom.element.orimodel.OriVertex;
import diamond.model.geom.util.DistanceUtil;

/**
 * @author long_
 *
 */
public class Folder {
    public static void fold(OriModel model) {
        simpleFoldWithoutZorder(model.getFaces(), model.getEdges());
    }

    private static void simpleFoldWithoutZorder(
            List<OriFace> faces, List<OriEdge> edges) {
        int id = 0;
        for (OriFace face : faces) {
            face.setFaceFront(true);
            face.setTmpFlg(false);
            face.setZ_order(0);
            ;
            face.setTmpInt(id);
            id++;
            for (OriHalfEdge he : face.halfEdges) {
                he.setTmpVec(he.getVertex().getP());
            }
        }

        walkFace(faces.get(0));

        afterMath(faces, edges);

    }

    private static void afterMath(List<OriFace> faces, List<OriEdge> edges) {
        for (OriEdge e : edges) {
            e.setEv(new OriVertex(e.getLeft().getTmpVec()));
            if (e.getRight() != null) {
                e.setEv(new OriVertex(e.getRight().getTmpVec()));
            }
            e.getSv().setTmpFlg(false);
            e.getEv().setTmpFlg(false);
        }

        for (OriFace face : faces) {
            for (OriHalfEdge he : face.halfEdges) {
                he.setPositionAfterFolded(he.getTmpVec());
            }
        }
    }

    // Recursive method that flips the faces, making the folds
    private static void walkFace(OriFace baseFace) {
        baseFace.setTmpFlg(true);
        for (OriHalfEdge he : baseFace.halfEdges) {
            if (he.getPair() == null) {
                continue;
            }
            OriFace adjuscentFace = he.getPair().getFace();
            if (adjuscentFace.isTmpFlg()) {
                continue;
            }
            flipFace(adjuscentFace, he);
            walkFace(adjuscentFace);
        }
    }

    private static void flipFace(OriFace face, OriHalfEdge baseHe) {
        Vector2d preOrigin = new Vector2d(
                baseHe.getPair().getNext().getTmpVec());
        Vector2d afterOrigin = new Vector2d(baseHe.getTmpVec());

        // Creates the base unit vector for before the rotation
        Vector2d baseDir = new Vector2d();
        baseDir.sub(baseHe.getPair().getTmpVec(),
                baseHe.getPair().getNext().getTmpVec());

        // Creates the base unit vector for after the rotation
        Vector2d afterDir = new Vector2d();
        afterDir.sub(baseHe.getNext().getTmpVec(),
                baseHe.getTmpVec());
        afterDir.normalize();

        Line preLine = new Line(preOrigin, baseDir);

        for (OriHalfEdge he : face.halfEdges) {
            double param[] = new double[1];
            double d0 = DistanceUtil.Distance(he.getTmpVec(), preLine,
                    param);
            double d1 = param[0];

            Vector2d footV = new Vector2d(afterOrigin);
            footV.x += d1 * afterDir.x;
            footV.y += d1 * afterDir.y;

            Vector2d afterDirFromFoot = new Vector2d();
            afterDirFromFoot.x = afterDir.y;
            afterDirFromFoot.y = -afterDir.x;

            he.getTmpVec().x = footV.x + d0 * afterDirFromFoot.x;
            he.getTmpVec().y = footV.y + d0 * afterDirFromFoot.y;
        }

        // Ivertion
        if (face.isFaceFront() == baseHe.getFace().isFaceFront()) {
            Vector2d ep = baseHe.getNext().getTmpVec();
            Vector2d sp = baseHe.getTmpVec();

            Vector2d b = new Vector2d();
            b.sub(ep, sp);
            for (OriHalfEdge he : face.halfEdges) {

                if (DistanceUtil.Distance(he.getTmpVec(),
                        new Line(sp, b)) < Constants.EPS) {
                    continue;
                }
                if (Math.abs(b.y) < Constants.EPS) {
                    Vector2d a = new Vector2d();
                    a.sub(he.getTmpVec(), sp);
                    a.y = -a.y;
                    he.getTmpVec().y = a.y + sp.y;
                } else {
                    Vector2d a = new Vector2d();
                    a.sub(he.getTmpVec(), sp);
                    he.getTmpVec().y = ((b.y * b.y - b.x * b.x) * a.y
                            + 2 * b.x * b.y * a.x) / b.lengthSquared();
                    he.getTmpVec().x = b.x / b.y * a.y - a.x
                            + b.x / b.y * he.getTmpVec().y;
                    he.getTmpVec().x += sp.x;
                    he.getTmpVec().y += sp.y;
                }
            }
            //Flip
            face.setFaceFront(!face.isFaceFront());
        }
    }
}
