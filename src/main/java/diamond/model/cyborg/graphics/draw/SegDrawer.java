/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class SegDrawer {
    final private static Color POINTED = Color.GREEN;
    final private static Color BASE = Color.BLACK;
    public static int CUT = 95;

    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            Ver<T> p,
            Ver<T> q,
            Ver<T> c,
            boolean isPointed) {
        g2d.setColor((isPointed) ? POINTED : BASE);
        g2d.setStroke(screen.getSegStroke((float) screen.getScale()));
        p = p.dir(c).mul(CUT).div(100).ver(c);
        q = q.dir(c).mul(CUT).div(100).ver(c);
        g2d.draw(ShapeBuilder.build(p, q));
    }

}
