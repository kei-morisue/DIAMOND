/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
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
public class PaintScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        for (Vertex v : cp.getVertices()) {//TODO
            draw(g2d, v);
        }
        for (Face face : cp.getFaces()) {
            if (!face.getProperty().isDisabled()) {
                draw(g2d, face);
            }
            for (HalfEdge he : face.getUnsettledLines()) {
                if (!he.getProperty().isDisabled()) {
                    draw(g2d, he);
                }
            }
            for (HalfEdge he : face.getCreaseLines()) {
                if (!he.getProperty().isDisabled()) {
                    draw(g2d, he);
                }
            }

        }
        for (HalfEdge he : cp.getHalfEdges()) {//TODO
            if (!he.getProperty().isDisabled()) {
                draw(g2d, he);
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
        g2d.setColor(HalfEdgeStyle.getColor(he));
        g2d.setStroke(HalfEdgeStyle.getCpStroke(he.getType()));
        g2d.draw(HalfEdgeDrawer.buildLine(he));
    }

    public static void draw(Graphics2D g2d, Context context) {
        context.getPaintAction().onDraw(g2d, context);
    }

    public static void drawTemporaryLine(Graphics2D g2d, Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        if (vertices.size() > 0) {
            Vertex picked = vertices.peek();
            g2d.setColor(HalfEdgeStyle.POINTED);
            g2d.setStroke(HalfEdgeStyle.STROKE_TEMPORARY);
            Double mousePoint = context.getMousePoint();
            g2d.draw(new Line2D.Double(picked.x, picked.y,
                    mousePoint.x,
                    mousePoint.y));
        }

    }

}
