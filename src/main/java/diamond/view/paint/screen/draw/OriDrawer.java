/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;

/**
 * @author long_
 *
 */
public class OriDrawer {
    public static void describe(Graphics2D g2d, Object pointed, int x, int y) {
        g2d.setColor(ColorStyle.DEBUGGING_STRING);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        if (pointed == null) {
            g2d.drawString("-", x, y);
            ;
        } else {
            g2d.drawString(pointed.toString(), x, y);
        }
        g2d.setTransform(tmpTransform);
        return;
    }

    public static void drawStepNo(Graphics2D g2d, int step, int size) {
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setFont(FontSetting.STEP_NO);
        g2d.setTransform(new AffineTransform());
        g2d.setColor(ColorStyle.STEP_NO);
        g2d.drawString(String.valueOf(step), 10, FontSetting.STEP_NO.getSize());
        g2d.setTransform(tmpTransform);

    }

    public static void drawLine(Graphics2D g2d, OriLine line, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                line.p1.y));
    }

    public static void drawHalfEdge(Graphics2D g2d, OriHalfEdge he, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(he.getSv().x, he.getSv().y, he.getEv().x,
                he.getEv().y));
    }

    public static void drawFoldedHalfEdge(
            Graphics2D g2d,
            OriHalfEdge he,
            Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(
                he.getSv().getFoldedPosition().getX(),
                he.getSv().getFoldedPosition().getY(),
                he.getEv().getFoldedPosition().getX(),
                he.getEv().getFoldedPosition().getY()));
    }

    public static void drawPoint(Graphics2D g2d, Point2D.Double point,
            double size, Color color) {
        double scaledSize = size;
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(
                point.x - scaledSize * 0.5,
                point.y - scaledSize * 0.5,
                scaledSize,
                scaledSize));
    }

    public static void drawVertex(Graphics2D g2d, Point2D.Double vertex,
            double size,
            Color color) {
        double scaledSize = size;
        g2d.setColor(color);
        Point2D.Double p = vertex;
        g2d.fill(new Rectangle2D.Double(
                p.x - scaledSize * 0.5,
                p.y - scaledSize * 0.5,
                scaledSize,
                scaledSize));
    }

    public static void drawFace(Graphics2D g2d,
            GeneralPath outLine, Color color) {
        g2d.setColor(color);
        g2d.fill(outLine);
    }

    public static void drawModel(Graphics2D g2d, OriModel model) {
        for (OriFace face : model.getFaces()) {
            drawFace(g2d, face.getFoldedOutline(), face.getColor());
            for (OriHalfEdge aux : face.getAuxLines()) {
                LineType type = aux.getType();
                type = (face.isFaceFront()) ? type : LineType.getPairType(type);
                drawFoldedHalfEdge(
                        g2d,
                        aux,
                        ColorStyle.getDiagramColor(type),
                        LineStrokeSetting.getDiagramStroke(type));
            }
            for (OriHalfEdge he : face.getHalfEdges()) {
                drawFoldedHalfEdge(
                        g2d,
                        he,
                        ColorStyle.ORI_HALFEDGE,
                        LineStrokeSetting.getDiagramStroke(he.getType()));
            }
        }
    }
}
