/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.util.Point2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Sink extends AbstractArrowBody {

    @Override
    public double getTailAngle(Double pT, Double pH) {
        return Point2DUtil.angle(Point2DUtil.sub(pH, pT));
    }

    @Override
    public double getHeadAngle(Double pT, Double pH) {
        return Point2DUtil.angle(Point2DUtil.sub(pT, pH));
    }

    @Override
    public void flip() {
    }

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            boolean isSelected) {
    }

}
