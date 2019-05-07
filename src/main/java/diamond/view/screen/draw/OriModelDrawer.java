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
import diamond.view.screen.draw.style.color.OriFaceColor;
import diamond.view.screen.draw.style.color.OriHalfEdgeColor;

/**
 * @author long_
 *
 */
public class OriModelDrawer {

    public static void drawModel(Graphics2D g2d, OriModel model) {
        double scale = G2DUtil.getScale(g2d);

        for (OriFace face : model.getFaces()) {
            OriFaceDrawer.drawFoldedFace(g2d, face,
                    OriFaceColor
                            .getColor(face.isFaceFront()));
            drawEdges(g2d, scale, face);
            drawCreaseLines(g2d, face);
            drawUnsettledLines(g2d, face);
        }
    }

    private static void drawEdges(Graphics2D g2d, double scale, OriFace face) {
        for (OriHalfEdge he : face.getHalfEdges()) {
            OriVertex sv = he.getSv();
            if (sv.isPickked()) {
                OriVertexDrawer.drawVertex(g2d, sv,
                        VertexStyle.SIZE_PICKED / scale);
            }

            LineType type = he.getType();
            OriHalfEdgeDrawer.drawFoldedHalfEdge(
                    g2d,
                    he,
                    OriHalfEdgeColor.ORI_HALFEDGE,
                    LineStyle.getDiagramStroke(type));
        }
    }

    private static void drawUnsettledLines(Graphics2D g2d, OriFace face) {
        for (OriHalfEdge he : face.getUnsettledLines()) {
            LineType type = he.getType();
            if (face.isFaceFront()) {
                type = LineType.getPairType(type);
            }
            OriHalfEdgeDrawer.drawFoldedHalfEdge(
                    g2d,
                    he,
                    OriHalfEdgeColor
                            .getDiagramColor(type),
                    LineStyle.getDiagramStroke(type));

        }
    }

    private static void drawCreaseLines(Graphics2D g2d, OriFace face) {
        for (OriHalfEdge he : face.getCreaseLines()) {
            OriHalfEdgeDrawer.drawFoldedCreaseLine(
                    g2d,
                    he,
                    OriHalfEdgeColor
                            .getDiagramColor(LineType.CREASE),
                    LineStyle.getDiagramStroke(LineType.CREASE),
                    LineStyle.CLIP_SCALE * 0.01);
        }
    }
}
