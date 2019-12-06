/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.head;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;

import diamond.model.symbol.arrow.body.AbstractArrowBody;
import diamond.view.ui.screen.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Sink extends AbstractArrowHead {
    public static final double kurtosis = Math.PI / 7;
    private final static double size = 50.0;
    static private final Color COLOR_BODY = Color.white;
    static private final Color COLOR_EDGE = Color.black;

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        Double position = (isTail) ? tail : head;
        affineTransform.translate(position.x, position.y);
        double angle = (isTail) ? body.getTailAngle(tail, head)
                : body.getHeadAngle(tail, head);
        affineTransform.rotate(angle);
        double scale = G2DUtil.getScale(g2d);
        Double o = new Double(0.0, 0.0);
        double scaled = size / scale;
        Double p = new Double(-scaled, scaled);
        Double q = new Double(-scaled, -scaled);
        Double r = new Double(-scaled, scaled / 2);
        Double s = new Double(-scaled, -scaled / 2);
        Double t = new Double(-scaled * 2, scaled / 2);
        Double u = new Double(-scaled * 2, -scaled / 2);

        affineTransform.transform(o, o);
        affineTransform.transform(p, p);
        affineTransform.transform(q, q);
        affineTransform.transform(r, r);
        affineTransform.transform(s, s);
        affineTransform.transform(t, t);
        affineTransform.transform(u, u);
        path.moveTo(o.x, o.y);
        path.lineTo(p.x, p.y);
        path.lineTo(r.x, r.y);
        path.lineTo(t.x, t.y);
        path.lineTo(u.x, u.y);
        path.lineTo(s.x, s.y);
        path.lineTo(q.x, q.y);
        path.closePath();
        g2d.setColor(COLOR_BODY);
        g2d.fill(path);
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_EDGE);
        g2d.setStroke(new BasicStroke((float) (3.0 / scale),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        g2d.draw(path);
    }

}
