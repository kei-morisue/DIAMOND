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
public class Mountain extends AbstractArrowHead {
    public static final double kurtosis = Math.PI / 7;
    private final static double size = 30.0;
    static private final Color COLOR_BODY = Color.white;
    static private final Color COLOR_EDGE = Color.black;

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
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
        g2d.setColor(COLOR_BODY);
        g2d.fill(path);
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_EDGE);
        g2d.draw(path);
    }

}
