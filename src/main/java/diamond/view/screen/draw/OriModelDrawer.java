/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Graphics2D;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.VertexStyle;

/**
 * @author long_
 *
 */
public class OriModelDrawer {

    public static void drawModel(Graphics2D g2d, OriModel model) {
        double scale = G2DUtil.getScale(g2d);

        for (OriFace face : model.getFaces()) {
            OriFaceDrawer.drawFoldedFace(g2d, face,
                    diamond.view.screen.draw.style.color.OriFace
                            .getColor(face.isFaceFront()));
            for (OriHalfEdge aux : face.getAuxLines()) {
                OriHalfEdgeDrawer.drawFoldedCreaseLine(
                        g2d,
                        aux,
                        diamond.view.screen.draw.style.color.OriHalfEdge
                                .getDiagramColor(LineType.CREASE),
                        LineStyle.getDiagramStroke(LineType.CREASE),
                        LineStyle.CLIP_SCALE * 0.01);
                if (aux.getArrow() != null) {
                    aux.getArrow().draw(g2d, aux);
                }
            }
            for (OriHalfEdge he : face.getUnettledLines()) {
                OriHalfEdgeDrawer.drawFoldedHalfEdge(
                        g2d,
                        he,
                        diamond.view.screen.draw.style.color.OriHalfEdge
                                .getDiagramColor(he.getType()),
                        LineStyle.getDiagramStroke(he.getType()));
                if (he.getArrow() != null) {
                    he.getArrow().draw(g2d, he);
                }

            }
            for (OriHalfEdge he : face.getHalfEdges()) {
                OriVertex sv = he.getSv();
                if (sv.isPickked()) {
                    OriVertexDrawer.drawVertex(g2d, sv,
                            VertexStyle.SIZE_PICKED / scale);
                }
                if (he.getArrow() != null) {
                    he.getArrow().draw(g2d, he);
                }
                OriHalfEdgeDrawer.drawFoldedHalfEdge(
                        g2d,
                        he,
                        diamond.view.screen.draw.style.color.OriHalfEdge.ORI_HALFEDGE,
                        LineStyle.getDiagramStroke(he.getType()));
            }
        }
    }
}
