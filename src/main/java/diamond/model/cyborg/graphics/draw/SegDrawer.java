/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Nodes;
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
            Float scale,
            Ver<T> p,
            Ver<T> q,
            Nodes<T> nodes,
            boolean isPointed) {
        Ver<T> c = q.dir(p).div(2).ver(p);
        g2d.setColor((isPointed) ? POINTED : BASE);
        g2d.setStroke(screen.getSegStroke(scale));
        p = p.dir(c).mul(CUT).div(100).ver(c);
        q = q.dir(c).mul(CUT).div(100).ver(c);
        g2d.draw(ShapeBuilder.build(p, q));
        nodes.draw(screen, g2d, scale, isPointed);
    }

}
