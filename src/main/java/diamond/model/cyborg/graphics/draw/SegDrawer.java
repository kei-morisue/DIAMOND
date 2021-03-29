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
public class SegDrawer {
    final private static int CAP = BasicStroke.CAP_BUTT;
    final private static int JOIN = BasicStroke.JOIN_ROUND;
    final private static Color POINTED = Color.GREEN;
    final private static Color BASE = Color.BLACK;
    final private static float WIDTH = 0.0f;
    final private static float W_POINTED = 0.0f;
    public static int CUT = 90;

    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> p,
            Ver<T> q,
            Ver<T> c) {
        g2d.setColor(BASE);
        g2d.setStroke(strokeModel(screen.getScale()));
        p = p.dir(c).mul(CUT).div(100).ver(c);
        q = q.dir(c).mul(CUT).div(100).ver(c);
        g2d.draw(ShapeBuilder.build(p, q));
    }

    //TODO
    public static <T extends F<T>> void drawPointed(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> p,
            Ver<T> q) {
        g2d.setColor(POINTED);
        g2d.setStroke(strokePointed(screen.getScale()));
        g2d.draw(
                ShapeBuilder.build(p, q));
    }

    public static BasicStroke strokeCp(double scale) {
        return new BasicStroke(WIDTH / (float) scale, CAP, JOIN);
    }

    public static BasicStroke strokeModel(double scale) {
        return new BasicStroke(WIDTH / (float) scale, CAP, JOIN);
    }

    public static BasicStroke strokePointed(double scale) {
        return new BasicStroke(W_POINTED / (float) scale, CAP, JOIN);
    }

}
