/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.body;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.Point2DUtil;;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractArrowBody {
    protected boolean isLHS = false;
    public static final Color COLOR_ARROW_BODY = Color.black;
    public static final Color COLOR_SELECTED = Color.green;

    protected AbstractArrowBody() {
    }

    public Point2D.Double getHeadPoint(Point2D.Double p0, Point2D.Double p1,
            double scale) {
        Point2D.Double center = CenterPointUtil.get(p0, p1);
        Point2D.Double dir = Point2DUtil.scale(
                Point2DUtil.per(Point2DUtil.sub(p1, center)), scale);
        return Point2DUtil.add(center, dir);
    };

    public Point2D.Double getTailPoint(Point2D.Double p0, Point2D.Double p1,
            double scale) {
        Point2D.Double center = CenterPointUtil.get(p0, p1);
        Point2D.Double dir = Point2DUtil.scale(
                Point2DUtil.per(Point2DUtil.sub(p0, center)), scale);
        return Point2DUtil.add(center, dir);
    };

    public abstract double getTailAngle(Point2D.Double pT, Point2D.Double pH);

    public abstract double getHeadAngle(Point2D.Double pT, Point2D.Double pH);

    public abstract void flip();

    public abstract void draw(Graphics2D g2d, Point2D.Double tail,
            Point2D.Double head,
            boolean isSelected);

    public boolean isLHS() {
        return this.isLHS;
    }

    @Deprecated
    public void setLHS(boolean isLHS) {
        this.isLHS = isLHS;
    }
}
