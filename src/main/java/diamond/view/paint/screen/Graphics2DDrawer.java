/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.List;

import javax.vecmath.Vector2d;

import diamond.paint.core.LineSetting;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.util.ElementSelector;
import diamond.value.OriLine;

/**
 * @author long_
 *
 */
public class Graphics2DDrawer {
    public static void showXnY(Graphics2D g2d, Vector2d candidate) {
        if (candidate == null) {
            return;
        }
        g2d.setColor(Color.BLACK);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.drawString(
                String.format("(%f, %f)", candidate.x, candidate.y),
                0,
                10);
        g2d.setTransform(tmpTransform);
        return;
    }

    public static void drawGridLine(Graphics2D g2d, double paperSize,
            int divNum) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(LineSetting.STROKE_GRID);
        double step = paperSize / divNum;
        double halfSize = paperSize / 2.0;
        for (int i = 1; i < divNum; i++) {
            g2d.draw(new Line2D.Double(
                    step * i - halfSize, -halfSize,
                    step * i - halfSize, halfSize));
            g2d.draw(new Line2D.Double(
                    -halfSize, step * i - halfSize,
                    halfSize, step * i - halfSize));
        }
        return;
    }

    public static void drawLines(Graphics2D g2d, Collection<OriLine> lines) {
        g2d.setStroke(LineSetting.STROKE_VALLEY);
        g2d.setColor(Color.black);
        for (OriLine line : lines) {
            if (line.typeVal == OriLine.TYPE_NONE
                    && !PaintConfig.dispAuxLines) {
                continue;
            }

            if ((line.typeVal == OriLine.TYPE_RIDGE
                    || line.typeVal == OriLine.TYPE_VALLEY)
                    && !PaintConfig.dispMVLines) {
                continue;
            }

            g2d.setColor(ElementSelector.selectColorByLineType(line.typeVal));
            g2d.setStroke(ElementSelector.selectStroke(line.typeVal));

            if (PaintConfig.mouseAction != null) {
                if (PaintContext.getInstance().getLines()
                        .contains(line) == false) {
                    g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                            line.p1.y));
                }
            }
        }
    }

    public static void drawCrossLines(Graphics2D g2d,
            List<OriLine> crossLines) {
        if (crossLines.isEmpty()) {
            return;
        }
        g2d.setStroke(LineSetting.STROKE_TMP_OUTLINE);
        g2d.setColor(Color.MAGENTA);
        for (OriLine line : crossLines) {
            Vector2d v0 = line.p0;
            Vector2d v1 = line.p1;
            g2d.draw(new Line2D.Double(v0.x, v0.y, v1.x, v1.y));
        }
    }

    public static void drawVertexRectangles(Graphics2D g2d,
            CreasePattern creasePattern) {
        g2d.setColor(Color.BLACK);
        final double size = 4.0 / calcScale(g2d);
        for (OriLine line : creasePattern) {
            if (!PaintConfig.dispAuxLines
                    && line.typeVal == OriLine.TYPE_NONE) {
                continue;
            }
            if (!PaintConfig.dispMVLines && (line.typeVal == OriLine.TYPE_RIDGE
                    || line.typeVal == OriLine.TYPE_VALLEY)) {
                continue;
            }
            drawVertexRectangle(g2d, line.p0, size);
            drawVertexRectangle(g2d, line.p1, size);
        }
    }

    public static void drawVertexRectangle(Graphics2D g2d, Vector2d v0,
            double size) {
        g2d.fill(new Rectangle2D.Double(
                v0.x - size * 0.5,
                v0.y - size * 0.5,
                size,
                size));

    }

    private static double calcScale(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        double scaledCosTheta = transform.getScaleX();
        double scaledSinTheta = transform.getShearY();
        return Math.hypot(scaledCosTheta, scaledSinTheta);
    }

}
