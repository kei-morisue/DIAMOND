/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriModelUtil;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.draw.style.ColorStyle;
import diamond.view.screen.draw.style.FontStyle;
import diamond.view.screen.draw.style.LineStrokeStyle;

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

    public static void drawStepNo(Graphics2D g2d, int step) {
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setFont(FontStyle.STEP_NO);
        g2d.setTransform(new AffineTransform());
        g2d.setColor(ColorStyle.STEP_NO);
        g2d.drawString(String.valueOf(step), 10, FontStyle.STEP_NO.getSize());
        g2d.setTransform(tmpTransform);
    }

    public static void drawFaceNo(Graphics2D g2d, OriFace oriFace,
            List<OriFace> faces) {
        OriVertex centerPoint = OriModelUtil.getCenterPoint(oriFace);
        double scaleY = g2d.getTransform().getScaleY();
        g2d.setFont(new Font("Arial", Font.BOLD, (int) (10 / scaleY)));
        g2d.setColor(Color.BLACK);
        g2d.drawString(
                String.valueOf(faces.indexOf(oriFace)),
                (int) centerPoint.x, (int) centerPoint.y);
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

    public static void drawPoint(
            Graphics2D g2d,
            Point2D.Double point,
            double size, Color color) {
        double scaledSize = size;
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(
                point.x - scaledSize * 0.5,
                point.y - scaledSize * 0.5,
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
                        LineStrokeStyle.getDiagramStroke(type));
            }
            for (OriHalfEdge he : face.getHalfEdges()) {
                drawFoldedHalfEdge(
                        g2d,
                        he,
                        ColorStyle.ORI_HALFEDGE,
                        LineStrokeStyle.getDiagramStroke(he.getType()));
            }
        }
    }
}
