/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Graphics2D;
import java.util.HashSet;

import diamond.model.cyborg.geom.d1.LoopedEdge;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class FaceDrawer {
    //    private static Color front = Color.GRAY;
    //    private static Color back = Color.WHITE;
    //    private static final Color POINTED = Color.GREEN;

    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            LoopedEdge<T> loop,
            HashSet<Crease<T>> creases,
            boolean isPointed) {
        loop.draw(screen, g2d, scale, isPointed);
        for (Crease<T> crease : creases) {
            crease.draw(screen, g2d, scale, isPointed);
        }
    }

}
