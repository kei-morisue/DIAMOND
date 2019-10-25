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
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrowColor;

/**
 * @author long_
 *
 */
public class SinkArrowHead extends AbstractArrowHead {
    private static final double size = 30.0;

    public SinkArrowHead(AbstractArrowBody body, boolean isTail) {
        super(body, isTail);
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        Double position = getPosition(p0, p1);
        affineTransform.translate(position.x, position.y);
        affineTransform.rotate(getAngle(p0, p1));

        Double o = new Double(0.0, 0.0);
        Double p = new Double(-size, size);
        Double q = new Double(-size, -size);
        Double r = new Double(-size, size / 2);
        Double s = new Double(-size, -size / 2);
        Double t = new Double(-size * 2, size / 2);
        Double u = new Double(-size * 2, -size / 2);

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
        g2d.setColor(OriArrowColor.ARROW_MOUNTAIN);
        g2d.fill(path);
        g2d.setColor(isSelected ? OriArrowColor.ARROW_SELECTED
                : OriArrowColor.ARROW_VALLEY);
        g2d.setStroke(LineStyle.SINK_ARROW_OUTLINE);
        g2d.draw(path);

    }

}
