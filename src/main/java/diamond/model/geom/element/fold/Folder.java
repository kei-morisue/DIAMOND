/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class Folder {
    public static void fold(OriModel oriModel) {
        for (OriFace face : oriModel.getFaces()) {
            face.initialize();
        }
        OriFace face = oriModel.getBaseFace();
        if (oriModel.isFlip()) {
            face.fold(AffineTransform.getScaleInstance(-1.0, 1.0));
            face.setFaceFront(true);

        } else {
            face.fold(new AffineTransform());
            face.setFaceFront(false);

        }
        for (OriHalfEdge he : face.getHalfEdges()) {
            setAffine(face.getTransform(), he);
        }
    }

    public static void setAffine(AffineTransform accumulatedTransform,
            OriHalfEdge he) {
        OriFace face = he.getPair().getFace();
        if (face.getTransform() != null || he.getPair().getNext() == null) {
            return;
        }
        face.setFaceFront(!he.getFace().isFaceFront());
        face.fold(
                createFlipTransform(he.getPair(), accumulatedTransform));
        for (OriHalfEdge walkHe : face.getHalfEdges()) {
            setAffine(face.getTransform(), walkHe);
        }
    }

    public static AffineTransform createFlipTransform(OriHalfEdge he,
            AffineTransform accumulatedtransform) {
        OriVertex v0 = he.getSv();
        OriVertex v1 = he.getEv();

        Point2D p0 = new Point2D.Double();
        Point2D p1 = new Point2D.Double();
        accumulatedtransform.transform(v0, p0);
        accumulatedtransform.transform(v1, p1);

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
