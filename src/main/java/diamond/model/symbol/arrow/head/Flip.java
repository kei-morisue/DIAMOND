/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.head;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;

import diamond.model.symbol.arrow.body.AbstractArrowBody;

/**
 * @author Kei Morisue
 *
 */
public class Flip extends AbstractArrowHead {
    public static final double kurtosis = Math.PI / 7;
    private final static double size = 30.0;
    static private final Color COLOR_BODY = Color.black;

    private void fillShape(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body) {

        AffineTransform transform = g2d.getTransform();
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        transform.transform(tail, tail);
        transform.transform(head, head);

        Double position = (isTail) ? tail : head;

        affineTransform.translate(position.x, position.y);
        double angle = (isTail) ? body.getTailAngle(tail, head)
                : body.getHeadAngle(tail, head);
        affineTransform.rotate(angle);

        Double o = new Double(size, 0);
        Double p = new Double(-size, size * Math.sin(kurtosis));
        Double q = new Double(-size, -size * Math.sin(kurtosis));
        affineTransform.transform(o, o);
        affineTransform.transform(p, p);
        affineTransform.transform(q, q);
        path.moveTo(o.x, o.y);
        path.lineTo(p.x, p.y);
        path.lineTo(q.x, q.y);
        path.closePath();
        g2d.setTransform(new AffineTransform());
        g2d.fill(path);
        g2d.setTransform(transform);
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_BODY);
        fillShape(g2d, tail, head, body);
    }

}
