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
public class HeadValley extends AbstractArrowHead {
    public static final double kurtosis = Math.PI / 7;
    private final static double size = 30.0;
    static private final Color COLOR_BODY = Color.black;

    private GeneralPath getShape(Double tail, Double head,
            AbstractArrowBody body) {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        Double position = (isTail) ? tail : head;
        affineTransform.translate(position.x, position.y);
        if (isTail) {
            affineTransform.rotate(body.getTailAngle(tail, head));
        } else {
            affineTransform.rotate(body.getHeadAngle(tail, head));
        }
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
        return path;
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_BODY);
        g2d.fill(getShape(tail, head, body));
    }

}
