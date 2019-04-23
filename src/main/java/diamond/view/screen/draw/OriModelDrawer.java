/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.draw.style.ColorStyle;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.VertexStyle;

/**
 * @author long_
 *
 */
public class OriModelDrawer {

    private static double getScale(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        return Math.hypot(transform.getScaleX(), transform.getShearX());
    }

    public static void drawModel(Graphics2D g2d, OriModel model) {
        double scale = getScale(g2d);

        for (OriFace face : model.getFaces()) {
            OriFaceDrawer.drawFace(g2d, face.getFoldedOutline(),
                    face.getColor());
            for (OriHalfEdge aux : face.getAuxLines()) {
                OriHalfEdgeDrawer.drawFoldedAuxLine(
                        g2d,
                        aux,
                        ColorStyle.getDiagramColor(LineType.CREASE),
                        LineStyle.getDiagramStroke(LineType.CREASE),
                        LineStyle.CLIP_SCALE * 0.01);
            }
            for (OriHalfEdge he : face.getUnettledLines()) {
                OriHalfEdgeDrawer.drawFoldedHalfEdge(
                        g2d,
                        he,
                        ColorStyle.getDiagramColor(he.getType()),
                        LineStyle.getDiagramStroke(he.getType()));
            }
            for (OriHalfEdge he : face.getHalfEdges()) {
                OriVertex sv = he.getSv();
                if (sv.isPickked()) {
                    OriVertexDrawer.drawVertex(g2d, sv,
                            VertexStyle.SIZE_PICKED / scale);
                }
                OriHalfEdgeDrawer.drawFoldedHalfEdge(
                        g2d,
                        he,
                        ColorStyle.ORI_HALFEDGE,
                        LineStyle.getDiagramStroke(he.getType()));
            }
        }
    }
}
