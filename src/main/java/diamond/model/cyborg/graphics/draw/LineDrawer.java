/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class LineDrawer {
    final private static int CAP = BasicStroke.CAP_BUTT;
    final private static int JOIN = BasicStroke.JOIN_ROUND;
    final private static Color BASE = Color.BLACK;
    final private static float WIDTH = 3.0f;

    public static <T extends F<T>> void drawPointed(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> p,
            Ver<T> q) {
        g2d.setColor(BASE);
        g2d.setStroke(strokeModel(screen.getScale()));
        g2d.draw(
                ShapeBuilder.build(p, q));
    }

    public static BasicStroke strokeModel(double scale) {
        return new BasicStroke(WIDTH / (float) scale, CAP, JOIN);
    }

}
