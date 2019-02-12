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

/**
 * @author long_
 *
 */
public class Folder {
    public static void fold(OriModel oriModel) {

        OriFace face = oriModel.getFaces().iterator().next();
        face.setTransform(new AffineTransform());
        for (OriHalfEdge he : face.getHalfEdges()) {
            setAffine(face.getTransform(), he);
        }

        for (OriFace f : oriModel.getFaces()) {
            System.out.println(f.getTransform());
        }
        System.out.println();
    }

    public static void setAffine(AffineTransform accumulatedTransform,
            OriHalfEdge he) {
        if (he.getPair() == null) {
            return;
        }
        OriFace face = he.getPair().getFace();
        if (face.getTransform() != null) {
            return;
        }
        face.setTransform(createFlipTransform(he, accumulatedTransform));
        for (OriHalfEdge walkHe : face.getHalfEdges()) {
            setAffine(face.getTransform(), walkHe);
        }
    }

    public static AffineTransform createFlipTransform(OriHalfEdge he,
            AffineTransform accumulatedtransform) {
        Vector2d v0 = he.getSv();
        Vector2d v1 = he.getNext().getSv();

        Point2D p0 = new Point2D.Double();
        Point2D p1 = new Point2D.Double();
        accumulatedtransform.transform(new Point2D.Double(v0.x, v0.y), p0);
        accumulatedtransform.transform(new Point2D.Double(v1.x, v1.y), p0);

        AffineTransform transform = new AffineTransform();
        double dx = p1.getX() - p0.getX();
        double dy = p1.getY() - p0.getY();
        double theta = Math.atan2(dy, dx);
        double x = p0.getX();
        double y = p0.getY();

        transform.translate(x, y);
        transform.rotate(theta);
        transform.scale(1.0, -1.0);
        transform.rotate(theta);
        transform.translate(-x, -y);
        transform.concatenate(accumulatedtransform);
        return transform;

    }
}
