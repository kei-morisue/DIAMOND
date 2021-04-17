/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Nodes;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class NodesDrawer {
    static public final Color POINTED = Color.GREEN;
    static public final Color BASE = Color.BLACK;
    static public final double SIZE = 7.0;
    static public final double SIZE_POINTED = 10.0;

    //TODO refactor
    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            T k,
            Ver<T> p,
            Nodes<T> nodes,
            boolean isPointed) {
        g2d.setColor((isPointed) ? POINTED : BASE);
        double size = ((isPointed) ? SIZE_POINTED : SIZE) / scale;
        for (Ver<T> n : nodes.getNodes()) {
            Dir<T> dn = n.dir(p);
            Ver<T> ver = dn.scale(k).ver(p);
            g2d.fill(ShapeBuilder.build(ver, size));
        }
    }

}
