/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Graphics2D;
import java.util.ArrayList;

import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class StepDrawer {
    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            ArrayList<Face<T>> faces,
            Face<T> baseFace) {
        for (Face<T> face : faces) {
            face.draw(screen, g2d, scale, false);
        }
    }

}
