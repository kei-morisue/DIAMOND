/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.head;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;
import java.util.Vector;

import diamond.model.cyborg.Cp;
import diamond.model.symbol.arrow.body.AbstractArrowBody;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Repeat extends AbstractArrowHead {
    private final static double w = 50.0;
    private final static double h = 30.0;
    final public static Font FONT_STEP_NO = new Font("Arial", Font.PLAIN, 50);
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
        g2d.setFont(FONT_STEP_NO);
        g2d.setColor(COLOR_FONT);
        int x = (int) position.x;
        int y = (int) position.y;
        int size = FONT_STEP_NO.getSize() >> 2;
        int s0 = cps.indexOf(cp0);
        int s1 = cps.indexOf(cp1);
        g2d.drawString(String.valueOf(s0 + 1), x - size * 3, y + size);
        g2d.drawString("~", x - size, y + size);
        g2d.drawString(String.valueOf(s1 + 1), x + size, y + size);
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        Double position = (isTail) ? tail : head;
        affineTransform.translate(position.x, position.y);
        double scale = G2DUtil.getScale(g2d);
        double scaledW = w / scale;
        double scaledH = h / scale;
        Double p = new Double(scaledW, scaledH);
        Double q = new Double(-w / scale, scaledH);
        Double r = new Double(-w / scale, -h / scale);
        Double s = new Double(scaledW, -h / scale);

        affineTransform.transform(p, p);
        affineTransform.transform(q, q);
        affineTransform.transform(r, r);
        affineTransform.transform(s, s);

        path.moveTo(p.x, p.y);
        path.lineTo(q.x, q.y);
        path.lineTo(r.x, r.y);
        path.lineTo(s.x, s.y);
        path.closePath();
        g2d.setColor(COLOR_BODY);
        g2d.fill(path);
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_EDGE);
        g2d.draw(path);
        drawStepNo(g2d, position);
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
