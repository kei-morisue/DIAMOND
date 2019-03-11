/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

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
    public static void fold(OriModel oriModel, FoldPolicy foldPolicy) {
        OriFace face = getBaseFace(oriModel);
        face.fold(new AffineTransform(), foldPolicy);
        face.setFaceFront(true);
        for (OriHalfEdge he : face.getHalfEdges()) {
            setAffine(face.getTransform(), he, foldPolicy);
        }
        oriModel.getFaces().sort(new OriFaceComparator());
    }

    public static OriFace getBaseFace(OriModel oriModel) {
        OriVertex origin = new OriVertex(0.1, 0.01);//TODO strange point
        for (OriFace face : oriModel.getFaces()) {
            if (OriFaceUtil.onFace(face, origin)) {
                return face;
            }
        }
        return null;
    }

    public static void setAffine(AffineTransform accumulatedTransform,
            OriHalfEdge he, FoldPolicy foldPolicy) {
        OriFace face = he.getPair().getFace();
        if (face.getTransform() != null || he.getPair().getNext() == null) {//TODO strange condition...
            return;
        }
        face.setFaceFront(!he.getFace().isFaceFront());
        face.fold(
                createFlipTransform(he.getPair(), accumulatedTransform),
                foldPolicy);
        for (OriHalfEdge walkHe : face.getHalfEdges()) {
            setAffine(face.getTransform(), walkHe, foldPolicy);
        }
    }

    public static AffineTransform createFlipTransform(OriHalfEdge he,
            AffineTransform accumulatedtransform) {
        OriVertex v0 = he.getSv();
        OriVertex v1 = he.getEv();

        Point2D p0 = new Point2D.Double();
        Point2D p1 = new Point2D.Double();
        accumulatedtransform.transform(v0.toPt2D(), p0);
        accumulatedtransform.transform(v1.toPt2D(), p1);

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
