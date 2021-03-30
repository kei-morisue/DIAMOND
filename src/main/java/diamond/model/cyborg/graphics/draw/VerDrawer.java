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
public final class VerDrawer {
    static public final Color POINTED = Color.GREEN;
    static public final Color BASE = Color.BLACK;
    static public final double SIZE = 7.0;
    static public final double SIZE_POINTED = 10.0;

    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            Ver<T> ver,
            boolean isPointed) {
        g2d.setColor((isPointed) ? POINTED : BASE);
        double size = ((isPointed) ? SIZE_POINTED : SIZE) / screen.getScale();
        g2d.fill(ShapeBuilder.build(ver, size));
    }

}
