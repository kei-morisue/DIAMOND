/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class CurvedArrowBody {
    private Point2D.Double startPoint;
    private Point2D.Double endPoint;
    private Point2D.Double middlePoint;

    public CurvedArrowBody() {

    }

    public CurvedArrowBody(Point2D.Double sp, Point2D.Double ep, boolean isLHS) {
        this.startPoint = sp;
        this.endPoint = ep;
        setMiddlePoint(isLHS);
    }

    public GeneralPath getStroke() {
        GeneralPath path = new GeneralPath();
        path.moveTo(startPoint.x, startPoint.y);
        path.curveTo(
                startPoint.x, startPoint.y,
                middlePoint.x, middlePoint.y,
                endPoint.x, endPoint.y);
        return path;
    }

    private void setMiddlePoint(boolean isLHS) {
        middlePoint = startPoint;

        Point2D.Double dir = Point2DUtil.sub(endPoint, startPoint);
        double length = Point2DUtil.diatance(startPoint, endPoint);
        double angle = Point2DUtil.angle(dir);
        middlePoint = Point2DUtil.sub(middlePoint,
                Point2DUtil.scale(dir, 0.25));
        if (isLHS) {
            angle -= Math.PI / 2;
        } else {
            angle += Math.PI / 2;
        }
        Point2D.Double norm = Point2DUtil.build(length / 4, angle);
        middlePoint = Point2DUtil.plus(middlePoint, norm);
    }

    /**
     * @return startPoint
     */
    public Point2D.Double getStartPoint() {
        return this.startPoint;
    }

    /**
     * @return endPoint
     */
    public Point2D.Double getEndPoint() {
        return this.endPoint;
    }

    /**
     * @return middlePoint
     */
    public Point2D.Double getMiddlePoint() {
        return this.middlePoint;
    }

}
