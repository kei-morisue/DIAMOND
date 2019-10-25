/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.head;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class RepeatArrowTail extends AbstractArrowHead {
    private final static double w = 50.0;
    private final static double h = 30.0;

    public RepeatArrowTail(AbstractArrowBody body, boolean isTail) {
        super(body, isTail);
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
    }

    private double getScale(AffineTransform affineTransform) {
        double x = affineTransform.getScaleX();
        double y = affineTransform.getScaleY();
        double scale = Math.hypot(x, y);
        return scale;
    }

}
