/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.symbol.Symbol;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;
import diamond.view.ui.screen.style.VertexStyle;

/**
 * @author Kei Morisue
 *
 */
public class FoldedScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        double scale = G2DUtil.getScale(g2d);
        for (Face face : cp.getFaces()) {
            if (face.getProperty().isDisabled()) {
                continue;
            }
            draw(g2d, face, scale);
            for (HalfEdge he : face.getSortedEdges()) {
                draw(g2d, he.getV0(), scale);
            }
            for (HalfEdge he : face.getUnsettledLines()) {
                drawUnsettled(g2d, he, scale);
                draw(g2d, he.getV0(), scale);
                draw(g2d, he.getV1(), scale);
            }
        }
        for (Symbol<Face> symbol : cp.getSymbolsFace().values()) {
            symbol.drawFolded(g2d);
        }
        for (Symbol<HalfEdge> symbol : cp.getSymbolsHalfEdge().values()) {
            symbol.drawFolded(g2d);
        }
        for (Symbol<Vertex> symbol : cp.getSymbolsVertex().values()) {
            symbol.drawFolded(g2d);
        }
    }

    public static void drawResult(Graphics2D g2d, Cp cp) {
        double scale = G2DUtil.getScale(g2d);
        for (Face face : cp.getFaces()) {
            if (face.getProperty().isDisabled()) {
                continue;
            }
            draw(g2d, face, scale);
            for (HalfEdge he : face.getSortedEdges()) {
                draw(g2d, he.getV0(), scale);

            }
            for (HalfEdge he : face.getUnsettledLines()) {
                if (he.getType() == EdgeType.CREASE) {
                    drawUnsettled(g2d, he, scale);
                    draw(g2d, he.getV0(), scale);
                    draw(g2d, he.getV1(), scale);
                }
            }

        }

    }

    public static void draw(Graphics2D g2d, Vertex v, double scale) {
        if (v.getProperty().isColored()) {
            double size = VertexStyle.getSize(v) / scale;
            g2d.setColor(VertexStyle.getColor(v));
            g2d.fill(VertexDrawer.buildVertex(v.getFoldedOffset(), size));
        }
    }

    public static void draw(
            Graphics2D g2d,
            Rectangle2D.Double rect) {
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.setColor(Color.BLACK);
        g2d.draw(rect);
        g2d.setTransform(tmpTransform);
    }

    public static void draw(Graphics2D g2d, HalfEdge he, double scale) {
        g2d.setColor(HalfEdgeStyle.getFoldedColor(he));
        g2d.setStroke(
                HalfEdgeStyle.getFoldedStroke(he.getType(), (float) scale,
                        he.getFace().isFaceFront()));
        g2d.draw(HalfEdgeDrawer.buildFoldedLine(he));
    }

    public static void drawUnsettled(Graphics2D g2d, HalfEdge he,
            double scale) {
        if (!he.getProperty().isDisabled()) {
            g2d.setColor(HalfEdgeStyle.getFoldedColor(he));
            EdgeType type = he.getType();
            g2d.setStroke(HalfEdgeStyle.getFoldedStroke(type, (float) scale,
                    he.getFace().isFaceFront()));
            if (type == EdgeType.CREASE) {
                g2d.draw(
                        HalfEdgeDrawer.buildFoldedLine(he,
                                HalfEdgeStyle.CLIP_SCALE));
            } else {
                g2d.draw(HalfEdgeDrawer.buildFoldedLine(he));

            }
        }
    }

    public static void draw(Graphics2D g2d, Face f, double scale) {
        g2d.setColor(FaceStyle.getFoldedColor(f));
        GeneralPath outline = FaceDrawer.buildFoldedOutline(f);
        g2d.fill(outline);
        g2d.setColor(HalfEdgeStyle.CUT);
        g2d.setStroke(
                HalfEdgeStyle.getFoldedStroke(EdgeType.CUT, (float) scale,
                        f.isFaceFront()));
        g2d.draw(outline);
    }

}
