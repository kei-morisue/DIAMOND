/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.awt.geom.AffineTransform;
import java.util.List;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.geom.element.orimodel.OriVertex;

/**
 * @author long_
 *
 */
public class Folder {

    public static void fold(OriModel model) {
        List<OriFace> faces = model.getFaces();
        OriFace baseFace = faces.get(0);
        baseFace.affineTransform.setToIdentity();
        walkFace(baseFace);
    }

    private static void walkFace(OriFace baseFace) {
        for (OriHalfEdge he : baseFace.halfEdges) {
            if (he.getPair() != null) {
                continue;
            }
            OriFace oriFace = he.getPair().getFace();
            if (oriFace.affineTransform != null) {
                continue;
            }
            oriFace.affineTransform = getTransform(he);
            walkFace(oriFace);
        }
    }

    private static AffineTransform getTransform(OriHalfEdge he) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToIdentity();
        double theta = getAngle(he);
        affineTransform.rotate(theta);
        affineTransform.scale(1.0, -1.0);
        affineTransform.rotate(-theta);
        affineTransform.concatenate(he.getFace().affineTransform);
        return affineTransform;
    }

    private static double getAngle(OriHalfEdge he) {
        Vector2d dir = new Vector2d();
        OriVertex p0 = he.getVertex();
        OriVertex p1 = he.getNext().getVertex();
        double dx = p0.getP().x - p1.getP().x;
        double dy = p0.getP().y - p1.getP().y;
        dir.set(dx, dy);
        return Math.atan2(dir.y, dir.x);
    }
}
