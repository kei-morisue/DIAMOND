/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.geom.element.orimodel.OriVertex;
import diamond.model.geom.util.OriFaceUtil;

/**
 * @author long_
 *
 */
public class Folder {
    public static void fold(OriModel oriModel) {
        OriFace face = getBaseFace(oriModel);
        face.setTransform(new AffineTransform());
        face.setFaceFront(true);
        for (OriHalfEdge he : face.getHalfEdges()) {
            setAffine(face.getTransform(), he);
        }
    }

    public static OriFace getBaseFace(OriModel oriModel) {
        OriVertex origin = new OriVertex(0.0, 0.0);
        for (OriFace face : oriModel.getFaces()) {
            if (OriFaceUtil.onFace(face, origin)) {
                return face;
            }
        }
        return null;
    }

    public static void setAffine(AffineTransform accumulatedTransform,
            OriHalfEdge he) {
        OriFace face = he.getPair().getFace();
        if (face.getTransform() != null || he.getPair().getNext() == null) {//TODO WIRD condition...
            return;
        }
        face.setFaceFront(!he.getFace().isFaceFront());
        face.setTransform(
                createFlipTransform(he.getPair(), accumulatedTransform));
        for (OriHalfEdge walkHe : face.getHalfEdges()) {
            setAffine(face.getTransform(), walkHe);
        }
    }

    public static AffineTransform createFlipTransform(OriHalfEdge he,
            AffineTransform accumulatedtransform) {
        Vector2d v0 = he.getSv();
        Vector2d v1 = he.getEv();

        Point2D p0 = new Point2D.Double();
        Point2D p1 = new Point2D.Double();
        accumulatedtransform.transform(new Point2D.Double(v0.x, v0.y), p0);
        accumulatedtransform.transform(new Point2D.Double(v1.x, v1.y), p1);

        AffineTransform transform = new AffineTransform();
        double dx = p1.getX() - p0.getX();
        double dy = p1.getY() - p0.getY();
        double theta = Math.atan2(dy, dx);
        double x = p0.getX();
        double y = p0.getY();

        transform.translate(x, y);
        transform.rotate(theta);
        transform.scale(1.0, -1.0);
        transform.rotate(-theta);
        transform.translate(-x, -y);
        transform.concatenate(accumulatedtransform);
        return transform;

    }
}
