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
    final public static float DASH_VALLEY[] = { 10.0f, 3.0f };
    final public static float DASH_MOUNTAIN[] = { 10.0f, 2.0f, 2.0f, 2.0f };
    final public static int CAP = BasicStroke.CAP_BUTT;
    final public static int JOIN = BasicStroke.JOIN_MITER;

    final public static Color COLOR_VALLEY = Color.BLUE;
    final public static Color COLOR_MOUNTAIN = Color.RED;
    final public static Color COLOR_EDGE = Color.BLACK;
    final public static Color COLOR_CREASE = Color.BLACK;

    private double widthEdge = 3.0;
    private double widthCrease = 0.0f;
    private double widthSymbol = 5.0f;
    private double widthMv = 3.0f;

    private double clip = 0.1;

    public StyleSegment() {
    }

    public BasicStroke strokeEdge(double scale) {
        return new BasicStroke((float) (widthEdge / scale), CAP, JOIN);
    }

    public BasicStroke strokeCrease(double scale) {
        return new BasicStroke((float) (widthCrease / scale), CAP, JOIN);
    }

    public BasicStroke strokeMv(double scale, boolean isMountain) {
        return new BasicStroke((float) (widthMv / scale), CAP, JOIN,
                10.0f, (isMountain) ? DASH_MOUNTAIN : DASH_VALLEY, 0.0f);
    }

    public BasicStroke strokeSymbol(double scale) {
        return new BasicStroke((float) (widthSymbol / scale), CAP, JOIN);
    }

    @Deprecated
    public double getWidthEdge() {
        return widthEdge;
    }

    public void setWidthEdge(double widthEdge) {
        this.widthEdge = widthEdge;
    }

    @Deprecated
    public double getWidthCrease() {
        return widthCrease;
    }

    public void setWidthCrease(double widthCrease) {
        this.widthCrease = widthCrease;
    }

    @Deprecated
    public double getWidthSymbol() {
        return widthSymbol;
    }

    public void setWidthSymbol(double widthSymbol) {
        this.widthSymbol = widthSymbol;
    }

    @Deprecated
    public double getWidthMv() {
        return widthMv;
    }

    public void setWidthMv(double widthMv) {
        this.widthMv = widthMv;
    }

    public double getClip() {
        return clip;
    }

    public void setClip(double clip) {
        this.clip = clip;
    }

}
