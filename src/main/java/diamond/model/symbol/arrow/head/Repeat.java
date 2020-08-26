/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.head;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import diamond.model.cyborg.Cp;
import diamond.model.symbol.arrow.body.AbstractArrowBody;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Repeat extends AbstractArrowHead {
    final public static Color COLOR_FONT = Color.black;
    final public static Color COLOR_EDGE = Color.black;
    final public static Color COLOR_BODY = Color.white;

    private Cp cp0;
    private Cp cp1;
    private Vector<Cp> cps;

    public Repeat(Cp cp0, Cp cp1, Vector<Cp> cps) {
        this.cp0 = cp0;
        this.cp1 = cp1;
        this.cps = cps;
    }

    private void drawStepNo(Graphics2D g2d, Double position) {
        Font font = new Font("Arial", Font.PLAIN, fontSize(g2d));
        g2d.setFont(font);
        g2d.setColor(COLOR_FONT);
        int x = (int) position.x;
        int y = (int) position.y;
        String str = str();
        g2d.drawString(str, x - (w(g2d) >> 1), y + (int) (h(g2d) * 0.4));
    }

    private int fontSize(Graphics2D g2d) {
        return (int) (50 / G2DUtil.getScale(g2d));
    }

    private String str() {
        int s0 = cps.indexOf(cp0);
        int s1 = cps.indexOf(cp1);
        String str = String.valueOf(s0 + 1) + " ~ " + String.valueOf(s1 + 1);
        return str;
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
        Double position = (isTail) ? tail : head;
        drawRect(g2d, isSelected, position);
        drawStepNo(g2d, position);
    }

    private void drawRect(Graphics2D g2d, boolean isSelected,
            Double position) {
        int w = w(g2d);
        int h = h(g2d);
        Rectangle2D.Double rect = new Rectangle2D.Double(
                position.x - (w >> 1),
                position.y - (h >> 1),
                w,
                h);
        g2d.setColor(COLOR_BODY);
        g2d.fill(rect);
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_EDGE);
        g2d.draw(rect);
    }

    private int h(Graphics2D g2d) {
        int h = fontSize(g2d);
        return h;
    }

    private int w(Graphics2D g2d) {
        int w = (int) (str().length() * fontSize(g2d)) >> 1;
        return w;
    }

    @Deprecated
    public Cp getCp0() {
        return cp0;
    }

    @Deprecated

    public void setCp0(Cp cp0) {
        this.cp0 = cp0;
    }

    @Deprecated

    public Cp getCp1() {
        return cp1;
    }

    @Deprecated

    public void setCp1(Cp cp1) {
        this.cp1 = cp1;
    }

    @Deprecated

    public Vector<Cp> getCps() {
        return cps;
    }

    @Deprecated

    public void setCps(Vector<Cp> cps) {
        this.cps = cps;
    }

}
