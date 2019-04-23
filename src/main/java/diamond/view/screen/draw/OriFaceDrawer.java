/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 * @author long_
 *
 */
public class OriFaceDrawer {

    public static void drawFace(Graphics2D g2d,
            GeneralPath outLine, Color color) {
        g2d.setColor(color);
        g2d.fill(outLine);
    }

}
