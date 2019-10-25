/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.head;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;
import java.util.Vector;

import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;
import diamond.view.screen.draw.style.FontStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;
import diamond.view.screen.draw.style.color.StringColor;

/**
 * @author long_
 *
 */
public class RepeatArrowTail extends AbstractArrowHead {
    private final static double w = 50.0;
    private final static double h = 30.0;
    private Diagram d0;
    private Diagram d1;
    private Vector<Diagram> diagrams;

    public RepeatArrowTail(AbstractArrowBody body, Diagram d0, Diagram d1,
            Vector<Diagram> diagrams) {
        super(body, false);
        this.d0 = d0;
        this.d1 = d1;
        this.diagrams = diagrams;
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        Double position = getPosition(p0, p1);
        affineTransform.translate(position.x, position.y);
        double scale = getScale(g2d.getTransform());
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
        g2d.setColor(OriArrowColor.ARROW_MOUNTAIN);
        g2d.fill(path);
        g2d.setColor(isSelected ? OriArrowColor.ARROW_SELECTED
                : OriArrowColor.ARROW_VALLEY);
        g2d.draw(path);
        drawStepNo(g2d, position);
    }

    private double getScale(AffineTransform affineTransform) {
        double x = affineTransform.getScaleX();
        double y = affineTransform.getScaleY();
        double scale = Math.hypot(x, y);
        return scale;
    }

    private void drawStepNo(Graphics2D g2d, Double position) {
        Font font = FontStyle.REPEAT_STEP_NO;
        g2d.setFont(font);
        g2d.setColor(StringColor.STEP_NO);
        int x = (int) position.x;
        int y = (int) position.y;
        int size = font.getSize() >> 2;
        int s0 = diagrams.indexOf(d0);
        int s1 = diagrams.indexOf(d1);
        g2d.drawString(String.valueOf(s0 + 1), x - size * 3, y + size);
        g2d.drawString("~", x - size, y + size);
        g2d.drawString(String.valueOf(s1 + 1), x + size, y + size);
    }

    @Deprecated
    public Diagram getD0() {
        return d0;
    }

    @Deprecated
    public void setD0(Diagram d0) {
        this.d0 = d0;
    }

    @Deprecated
    public Diagram getD1() {
        return d1;
    }

    @Deprecated
    public void setD1(Diagram d1) {
        this.d1 = d1;
    }

    @Deprecated
    public Vector<Diagram> getDiagrams() {
        return diagrams;
    }

    @Deprecated
    public void setDiagrams(Vector<Diagram> diagrams) {
        this.diagrams = diagrams;
    }
}
