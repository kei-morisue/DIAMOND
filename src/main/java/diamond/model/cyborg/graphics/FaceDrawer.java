/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class FaceDrawer {
    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            LinkedList<Ver<T>> vers,
            HashSet<Seg<T>> creases) {
        Ver<T> wer = vers.getLast();
        g2d.setColor(Color.white);//TODO
        g2d.fill(ShapeBuilder.build(vers));
        for (Seg<T> crease : creases) {
            crease.draw(screen, vers, g2d);
        }
        for (Ver<T> ver : vers) {
            new Link<T>(null, null, wer, ver).draw(screen, g2d);
            wer = ver;
        }
    }
}
