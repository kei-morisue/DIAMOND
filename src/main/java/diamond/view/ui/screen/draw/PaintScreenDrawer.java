/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.FaceProperty;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.symbol.Symbol;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;
import diamond.view.ui.screen.style.VertexStyle;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        double scale = G2DUtil.getScale(g2d);
        for (Face face : cp.getFaces()) {
            draw(g2d, face, scale);
        }
        for (HalfEdge he : cp.getHalfEdges()) {
            draw(g2d, he, scale);
        }
        for (Vertex v : cp.getVertices()) {
            draw(g2d, v, scale);
        }
        for (Symbol<Face> symbol : cp.getSymbolsFace().values()) {
            symbol.drawCp(g2d);
        }
        for (Symbol<HalfEdge> symbol : cp.getSymbolsHalfEdge().values()) {
            symbol.drawCp(g2d);
        }
        for (Symbol<Vertex> symbol : cp.getSymbolsVertex().values()) {
            symbol.drawCp(g2d);
        }
        drawBase(g2d, cp, scale);
    }

    public static void draw(Graphics2D g2d, Vertex v, double scale) {
        double size = VertexStyle.getSize(v) / scale;
        g2d.setColor(VertexStyle.getColor(v));
        g2d.fill(VertexDrawer.buildVertex(v, size));
    }

    public static void draw(Graphics2D g2d, Face f, double scale) {
        g2d.setColor(FaceStyle.getCpColor(f));
        FaceProperty property = f.getProperty();
        if (property.isDisabled() && !property.isColored()) {
            g2d.setPaint(FaceDrawer.getIchimatsu());
        }
        g2d.fill(FaceDrawer.buildOutline(f, FaceStyle.CP_FACE_SCALE));
    }

    public static void draw(Graphics2D g2d, HalfEdge he, double scale) {
        if (!he.getProperty().isDisabled()) {
            g2d.setColor(HalfEdgeStyle.getCpColor(he));
            g2d.setStroke(HalfEdgeStyle.getCpStroke(he.getType()));
            g2d.draw(HalfEdgeDrawer.buildLine(he));
        }
    }

    public static void draw(Graphics2D g2d, Context context) {
        context.getPaintAction().onDraw(g2d, context);
    }

    private static void drawBase(Graphics2D g2d, Cp cp, double scale) {
        Face baseFace = cp.buildBaseFace();
        Double p = CenterPointUtil.get(baseFace);
        drawRedCross(g2d, p.x, p.y, scale);
    }

    private static void drawRedCross(
            Graphics2D g2d, double x, double y, double scale) {
        g2d.setColor(Color.red);
        double size = 2.0 / scale;
        double w = 6.0 * size;
        double h = 2.0 * size;
        double halfW = 3.0 * size;
        g2d.fill(new Rectangle2D.Double(x - halfW, y - size, w, h));
        g2d.fill(new Rectangle2D.Double(x - size, y - halfW, h, w));
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
