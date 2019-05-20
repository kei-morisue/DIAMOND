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
public class FlipArrowHead extends AbstractArrowHead {
    public static final double kurtosis = Math.PI / 7;
    private final static double size = 15.0;

    public FlipArrowHead(AbstractArrowBody body, boolean isTail) {
        super(body, isTail);
    }

    private void fillShape(Graphics2D g2d, Double p0, Double p1) {

        AffineTransform transform = g2d.getTransform();
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        transform.transform(p0, p0);
        transform.transform(p1, p1);

        Double position = getPosition(p0, p1);

        affineTransform.translate(position.x, position.y);
        affineTransform.rotate(getAngle(p0, p1));
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
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        g2d.setColor(isSelected ? OriArrowColor.ARROE_SELECTED
                : OriArrowColor.ARROW_BODY);
        fillShape(g2d, p0, p1);
    }

}
