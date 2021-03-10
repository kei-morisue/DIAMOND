/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;

/**
 * @author Kei Morisue
 *
 */
public final class FaceDrawer {
    public static <T extends F<T>> void draw(
            ScreenCp screen,
            LinkedList<Ver<T>> vers,
            HashSet<Seg<T>> creases) {

    }
}
