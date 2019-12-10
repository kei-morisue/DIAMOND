/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.body;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Repeat extends AbstractArrowBody {
    public static final Color COLOR_BODY = Color.black;
    public static final double SCALE_CROSS = 0.2;

    @Override
    public double getTailAngle(Double pT, Double pH) {
        Double p = Point2DUtil.sub(pT, pH);
        return Point2DUtil.angle(p);
    }

    @Override
    public double getHeadAngle(Double pT, Double pH) {
        return .0;

    }

    @Override
    public Double getTailPoint(Double p0, Double p1, double scale) {
        return CenterPointUtil.get(p0, p1);
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            boolean isSelected) {
        g2d.setColor(isSelected ? COLOR_SELECTED : COLOR_BODY);
        g2d.setStroke(new BasicStroke((float) (3.0 / G2DUtil.getScale(g2d)),
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.draw(getBody(tail, head));
        g2d.draw(getCross(tail, head));
    }

    @Override
    public void flip() {
        isLHS = !isLHS;
    }

    private GeneralPath getBody(Double tail, Double head) {
        GeneralPath path = new GeneralPath();
        path.moveTo(tail.x, tail.y);
        path.lineTo(head.x, head.y);
        return path;
    }

    private GeneralPath getCross(Double tail, Double head) {
        GeneralPath path = new GeneralPath();
        Double c = CenterPointUtil.get(tail, head);
        Double per = Point2DUtil.scale(
                Point2DUtil.per(Point2DUtil.sub(tail, c)),
                SCALE_CROSS);
        Double p0 = Point2DUtil.sub(c, per);
        Double p1 = Point2DUtil.add(c, per);
        path.moveTo(p0.x, p0.y);
        path.lineTo(p1.x, p1.y);
        return path;
    }

}
