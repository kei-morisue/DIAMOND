/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.head;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;

/**
 * @author long_
 *
 */
public class EmptyArrowHead extends AbstractArrowHead {

    public EmptyArrowHead(AbstractArrowBody body, boolean isTail) {
        super(body, isTail);
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
    }
}
