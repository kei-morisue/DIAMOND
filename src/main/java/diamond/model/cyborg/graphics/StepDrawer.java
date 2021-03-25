/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.awt.Graphics2D;
import java.util.ArrayList;

import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StepDrawer {
    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            ArrayList<Face<T>> faces,
            Face<T> baseFace) {
        for (Face<T> face : faces) {
            face.draw(screen, g2d);
        }
    }

    public static <T extends F<T>> void draw(
            ScreenCp<T> screen,
            Graphics2D g2d,
            ArrayList<Face<T>> faces) {
        for (Face<T> face : faces) {
            face.draw(screen, g2d);
        }
    }
}
