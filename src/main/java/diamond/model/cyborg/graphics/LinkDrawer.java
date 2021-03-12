/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class LinkDrawer {
    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> p,
            Ver<T> q) {
        g2d.setColor(Color.BLACK);//TODO
        g2d.draw(
                ShapeBuilder.build(p, q));
    }
}
