/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class ArrowHead {
    public static final double kurtosis = Math.PI / 9;
    private Point2D.Double o = new Point2D.Double();
    private Point2D.Double p;
    private Point2D.Double q;

    Point2D.Double sp;
    private double angle;

    public ArrowHead() {

    }

    public ArrowHead(CurvedArrowBody arrowBody, double size, boolean isTail) {
        this.p = new Point2D.Double(
                -size * Math.cos(kurtosis),
                size * Math.sin(kurtosis));
        this.q = new Point2D.Double(
                -size * Math.cos(kurtosis),
                -size * Math.sin(kurtosis));
        Double endPoint = arrowBody.getEndPoint();
        Double startPoint = arrowBody.getStartPoint();
        if (isTail) {
            this.sp = startPoint;
        } else {
            this.sp = endPoint;
        }
        Double middlePoint = arrowBody.getMiddlePoint();
        angle = Point2DUtil.angle(Point2DUtil.sub(this.sp, middlePoint));
    }

    public GeneralPath getShape() {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(sp.x, sp.y);
        affineTransform.rotate(angle);
        affineTransform.translate(-p.x, 0.0);
        affineTransform.transform(o, o);
        affineTransform.transform(p, p);
        affineTransform.transform(q, q);
        path.moveTo(o.x, o.y);
        path.lineTo(p.x, p.y);
        path.lineTo(q.x, q.y);
        path.closePath();
        return path;
    }
}
