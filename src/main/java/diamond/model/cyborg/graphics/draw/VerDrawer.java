/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class VerDrawer {
    static public final Color POINTED = Color.GREEN;
    static public final Color DEFAULT = Color.BLACK;
    static public final double SIZE = 7.0;
    static public final double SIZE_POINTED = 10.0;

    public static <T extends F<T>> void drawPointed(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> ver) {
        g2d.setColor(POINTED);
        double size = SIZE_POINTED / screen.getScale();
        g2d.fill(ShapeBuilder.build(ver, size));
    }

    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> ver) {
        g2d.setColor(DEFAULT);
        double size = SIZE / screen.getScale();
        g2d.fill(ShapeBuilder.build(ver, size));
    }

}
