/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.model.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.view.ui.screen.draw.FaceDrawer;
import diamond.view.ui.screen.draw.HalfEdgeDrawer;
import diamond.view.ui.screen.draw.VertexDrawer;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;
import diamond.view.ui.screen.style.VertexStyle;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        for (Vertex v : cp.getVertices()) {
            draw(g2d, v);
        }
        for (HalfEdge he : cp.getHes()) {
            draw(g2d, he);
        }
        for (Face face : cp.getFaces()) {
            if (!face.getProperty().isDisabled()) {
                draw(g2d, face);
            }
        }
    }

    public static void draw(Graphics2D g2d, Vertex v) {
        double size = VertexStyle.getSize(v) / G2DUtil.getScale(g2d);
        g2d.setColor(VertexStyle.getColor(v));
        g2d.fill(VertexDrawer.buildVertex(v, size));
    }

    public static void draw(Graphics2D g2d, Face f) {
        g2d.setColor(FaceStyle.COLOR_FRONT);
        g2d.fill(FaceDrawer.buildOutline(f, FaceStyle.CP_FACE_SCALE));
    }

    public static void draw(Graphics2D g2d, HalfEdge he) {
        g2d.setColor(HalfEdgeStyle.getColor(he.getType()));
        g2d.setStroke(HalfEdgeStyle.getCpStroke(he.getType()));
        g2d.draw(HalfEdgeDrawer.buildLine(he));
    }

}
