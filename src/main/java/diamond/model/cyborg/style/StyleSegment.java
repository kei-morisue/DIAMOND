/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.style;

import java.awt.BasicStroke;
import java.awt.Color;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.Util;

/**
 * @author Kei Morisue
 *
 */
public class StyleSegment {
    final public static int CAP = BasicStroke.CAP_BUTT;
    final public static int JOIN = BasicStroke.JOIN_ROUND;
    final public static Color COLOR_VALLEY = Color.BLUE;
    final public static Color COLOR_MOUNTAIN = Color.RED;
    final public static Color POINTED = Color.GREEN;
    final public static Color COLOR_EDGE = Color.BLACK;
    final public static Color COLOR_CREASE = Color.GRAY;
    final public static Color COLOR_STEP_CREASE = Color.BLACK;
    private float widthEdge = 3.0f;
    private float widthPointed = 3.0f;
    private float widthCrease = 0.0f;
    private float widthSymbol = 5.0f;
    private float widthMv = 3.0f;
    private float widthCp = 0.0f;

    private double clip = 0.1;

    public StyleSegment() {
    }

    public BasicStroke strokeCp(float scale) {
        return new BasicStroke(widthCp / scale, CAP, JOIN);
    }

    public BasicStroke strokeEdge(float scale) {
        return new BasicStroke(widthEdge / scale, CAP, JOIN);
    }

    public BasicStroke strokePointed(float scale) {
        return new BasicStroke(widthPointed / scale, CAP, JOIN);
    }

    public double getClipped(Face face, Vertex v1) {
        return (face.isBoundary(v1)) ? clip : 1.0;
    }

    public Color getColor(SegmentType type) {
        switch (type) {
        case CREASE_MOUNTAIN:
        case MOUNTAIN:
            return COLOR_MOUNTAIN;
        case CREASE_VALLEY:
        case VALLEY:
            return COLOR_VALLEY;
        default:
            return COLOR_CREASE;
        }
    }

    public Color getStepColor(SegmentType type) {
        switch (type) {
        case CREASE_MOUNTAIN:
        case MOUNTAIN:
            return COLOR_MOUNTAIN;
        case CREASE_VALLEY:
        case VALLEY:
            return COLOR_VALLEY;
        default:
            return COLOR_STEP_CREASE;
        }
    }

    public BasicStroke strokeCrease(float scale, SegmentType type) {
        switch (type) {
        case CREASE_MOUNTAIN:
            float dashM[] = { 10.0f / scale, 2.0f / scale, 2.0f / scale,
                    2.0f / scale };
            return new BasicStroke(
                    widthMv / scale, CAP, JOIN,
                    10.0f,
                    dashM,
                    0.0f);
        case CREASE_VALLEY:
            float dashV[] = { 10.0f / scale, 3.0f / scale };
            return new BasicStroke(
                    widthMv / scale, CAP, JOIN,
                    10.0f,
                    dashV,
                    0.0f);
        default:
            return new BasicStroke(widthCrease / scale, CAP, JOIN);
        }
    }

    public BasicStroke strokeSymbol(float scale) {
        return new BasicStroke(widthSymbol / scale, CAP, JOIN);
    }

    @Deprecated
    public double getWidthEdge() {
        return widthEdge;
    }

    @Deprecated
    public void setWidthEdge(float widthEdge) {
        this.widthEdge = widthEdge;
    }

    @Deprecated
    public double getWidthCrease() {
        return widthCrease;
    }

    @Deprecated
    public void setWidthCrease(float widthCrease) {
        this.widthCrease = widthCrease;
    }

    @Deprecated
    public double getWidthSymbol() {
        return widthSymbol;
    }

    public void setWidthSymbol(float widthSymbol) {
        this.widthSymbol = widthSymbol;
    }

    @Deprecated
    public double getWidthMv() {
        return widthMv;
    }

    @Deprecated
    public void setWidthMv(float widthMv) {
        this.widthMv = widthMv;
    }

    public double getClip() {
        return clip;
    }

    public void setClip(double clip) {
        this.clip = Util.hairCut(clip, .0, 1.0);
    }

    @Deprecated
    public float getWidthPointed() {
        return widthPointed;
    }

    @Deprecated
    public void setWidthPointed(float widthPointed) {
        this.widthPointed = widthPointed;
    }

    @Deprecated
    public float getWidthCp() {
        return widthCp;
    }

    @Deprecated
    public void setWidthCp(float widthCp) {
        this.widthCp = widthCp;
    }

}
