/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.style;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * @author Kei Morisue
 *
 */
public class StyleSegment {
    final public static int CAP = BasicStroke.CAP_BUTT;
    final public static int JOIN = BasicStroke.JOIN_ROUND;
    final public static Color COLOR_VALLEY = Color.BLUE;
    final public static Color COLOR_MOUNTAIN = Color.RED;
    final public static Color COLOR_EDGE = Color.BLACK;
    final public static Color COLOR_CREASE = Color.BLACK;

    private float widthEdge = 3.0f;
    private float widthCrease = 0.0f;
    private float widthSymbol = 5.0f;
    private float widthMv = 3.0f;

    private double clip = 0.1;

    public StyleSegment() {
    }

    public BasicStroke strokeEdge(float scale) {
        return new BasicStroke(widthEdge / scale, CAP, JOIN);
    }

    public BasicStroke strokeCrease(float scale) {
        return new BasicStroke(widthCrease / scale, CAP, JOIN);
    }

    public BasicStroke strokeMv(float scale, boolean isMountain) {
        float dashV[] = { 10.0f / scale, 3.0f / scale };
        float dashM[] = { 10.0f / scale, 2.0f / scale, 2.0f / scale,
                2.0f / scale };
        return new BasicStroke(
                widthMv / scale, CAP, JOIN,
                10.0f,
                (isMountain) ? dashM : dashV,
                0.0f);
    }

    public BasicStroke strokeSymbol(float scale) {
        return new BasicStroke(widthSymbol / scale, CAP, JOIN);
    }

    @Deprecated
    public double getWidthEdge() {
        return widthEdge;
    }

    public void setWidthEdge(float widthEdge) {
        this.widthEdge = widthEdge;
    }

    @Deprecated
    public double getWidthCrease() {
        return widthCrease;
    }

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

    public void setWidthMv(float widthMv) {
        this.widthMv = widthMv;
    }

    public double getClip() {
        return clip;
    }

    public void setClip(double clip) {
        this.clip = clip;
    }

}
