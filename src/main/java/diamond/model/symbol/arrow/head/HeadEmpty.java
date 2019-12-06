/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.head;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.symbol.arrow.body.AbstractArrowBody;

/**
 * @author Kei Morisue
 *
 */
public class HeadEmpty extends AbstractArrowHead {

    @Override
    public void draw(Graphics2D g2d, Double tail, Double head,
            AbstractArrowBody body, boolean isSelected) {
    }

}
