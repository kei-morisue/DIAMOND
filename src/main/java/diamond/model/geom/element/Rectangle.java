/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element;

import java.awt.geom.Point2D;
import java.util.Collection;

import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class Rectangle {
    private double x0;
    private double x1;
    private double y0;
    private double y1;

    public Rectangle() {
    }

    public Rectangle(OriHalfEdge he) {
        OriVertex sv = he.getSv();
        OriVertex ev = he.getEv();
        x0 = Math.min(sv.x, ev.x);
        x1 = Math.max(sv.x, ev.x);
        y0 = Math.min(sv.y, ev.y);
        y1 = Math.max(sv.y, ev.y);
    }

    public Rectangle(Collection<OriHalfEdge> hes) {
        Rectangle rectangle = null;
        for (OriHalfEdge he : hes) {
            if (rectangle == null) {
                rectangle = new Rectangle(he);
            } else {
                rectangle.merge(new Rectangle(he));
            }
        }
        x0 = rectangle.x0;
        x1 = rectangle.x1;
        y0 = rectangle.y0;
        y1 = rectangle.y1;
    }

    public void merge(Rectangle re) {
        x0 = Math.min(x0, re.x0);
        x1 = Math.max(x1, re.x1);
        y0 = Math.min(y0, re.y0);
        y1 = Math.max(y1, re.y1);
    }

    public Point2D.Double getCenter() {
        return new Point2D.Double((x0 + x1) / 2, (y0 + y1) / 2);
    }
}
