/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.diagram;

import java.awt.Graphics2D;

/**
 * @author long_
 *
 */
public abstract class ArrowDrawer {

    abstract void drawPath(Graphics2D g2d);

    abstract void drawArrowHead(Graphics2D g2d);

    abstract void drawArrowTail(Graphics2D g2d);

    void draw(Graphics2D g2d) {
        drawPath(g2d);
        drawArrowHead(g2d);
        drawArrowTail(g2d);
    };
}
