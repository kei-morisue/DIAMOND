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
public final class VerDrawer {
    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            Ver<T> ver) {
        g2d.setColor(Color.green);//TODO
        double size = 10 / screen.getScale();//TODO
        g2d.fill(ShapeBuilder.build(ver, size));
    }
}
