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
public class StraightArrowBody {
    private Point2D.Double startPoint;
    private Point2D.Double endPoint;
    private Point2D.Double middlePoint;

    public StraightArrowBody() {

    }

    public StraightArrowBody(Point2D.Double sp, Point2D.Double ep) {
        this.startPoint = sp;
        this.endPoint = ep;
        setMiddlePoint();
    }

    public GeneralPath getStroke() {
        GeneralPath path = new GeneralPath();
        path.moveTo(startPoint.x, startPoint.y);
        path.lineTo(endPoint.x, endPoint.y);
        return path;
    }

    private void setMiddlePoint() {
        middlePoint = Point2DUtil.plus(startPoint, endPoint);
        middlePoint = Point2DUtil.scale(middlePoint, 0.5);
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
