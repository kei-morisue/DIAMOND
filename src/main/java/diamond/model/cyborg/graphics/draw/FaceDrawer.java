/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
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
            LinkedList<Link<T>> edges,
            HashSet<Seg<T>> creases,
            LinkedList<Ver<T>> vers,
            boolean isPointed) {
        g2d.setColor(Color.white);//TODO
        g2d.fill(ShapeBuilder.build(vers));
        for (Link<T> edge : edges) {
            edge.draw(screen, g2d, scale, isPointed);
        }
        for (Seg<T> crease : creases) {
            crease.draw(screen, g2d, scale, isPointed);
        }
    }

}
