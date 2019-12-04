/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.view.ui.screen.G2DUtil;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;
import diamond.view.ui.screen.style.VertexStyle;

/**
 * @author Kei Morisue
 *
 */
public class FoldedScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        float scale = (float) G2DUtil.getScale(g2d);
        for (Face face : cp.getFaces()) {
            draw(g2d, face);
            for (HalfEdge he : face.getHalfEdges()) {
                draw(g2d, he, scale);
                draw(g2d, he.getV0(), scale);
            }
            for (HalfEdge he : face.getUnsettledLines()) {
                drawUnsettled(g2d, he, scale);
                draw(g2d, he.getV0(), scale);
                draw(g2d, he.getV1(), scale);
            }
        }
    }

    public static void draw(Graphics2D g2d, Vertex v, float scale) {
        if (v.getProperty().isColored()) {
            double size = VertexStyle.getSize(v) / scale;
            g2d.setColor(VertexStyle.getColor(v));
            g2d.fill(VertexDrawer.buildVertex(v, size));
        }
    }

    public static void draw(Graphics2D g2d, HalfEdge he, float scale) {
        g2d.setColor(HalfEdgeStyle.getFoldedColor(he));
        g2d.setStroke(HalfEdgeStyle.getFoldedStroke(he.getType(), scale));
        g2d.draw(HalfEdgeDrawer.buildFoldedLine(he));
    }

    public static void drawUnsettled(Graphics2D g2d, HalfEdge he, float scale) {
        if (!he.getProperty().isDisabled()) {
            g2d.setColor(HalfEdgeStyle.getFoldedColor(he));
            EdgeType type = he.getType();
            g2d.setStroke(HalfEdgeStyle.getFoldedStroke(type, scale));
            if (type == EdgeType.CREASE) {
                g2d.draw(
                        HalfEdgeDrawer.buildFoldedLine(he,
                                HalfEdgeStyle.CLIP_SCALE));
            } else {
                g2d.draw(HalfEdgeDrawer.buildFoldedLine(he));

            }
        }
    }

    public static void draw(Graphics2D g2d, Face f) {
        if (!f.getProperty().isDisabled()) {
            g2d.setColor(FaceStyle.getColor(f));
            g2d.fill(FaceDrawer.buildFoldedOutline(f));
        }
    }

    public static void draw(Graphics2D g2d, Context context) {
    }
}
