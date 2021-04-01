/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class LoopedEdgeDrawer {
    //    private static Color front = Color.GRAY;
    //    private static Color back = Color.WHITE;
    final private static Color BASE = Color.BLACK;
    final private static Color POINTED = Color.GREEN;

    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            Float scale,
            Collection<Edge<T>> edges,
            List<Ver<T>> vers,
            boolean isPointed) {
        g2d.setColor((isPointed) ? POINTED : BASE);
        g2d.setColor(Color.white);//TODO
        g2d.fill(ShapeBuilder.build(vers));
        for (Edge<T> edge : edges) {
            edge.draw(screen, g2d, scale, isPointed);
        }
    }

}
