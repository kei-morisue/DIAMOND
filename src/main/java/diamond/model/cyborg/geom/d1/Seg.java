/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.SegDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Seg<T extends F<T>> extends D1<T> implements Serializable {
    @Deprecated
    public Seg() {
    }

    public Seg(Ver<T> p, Ver<T> q) {
        super(p, q);
    }

    public void draw(ScreenModel<T> screen, List<Ver<T>> vers, Graphics2D g2d) {
        boolean cutP = false;
        boolean cutQ = false;
        for (Ver<T> ver : vers) {
            if (p == ver) {
                cutP = true;
            }
            if (q == ver) {
                cutQ = true;
            }
        }
        SegDrawer.draw(screen, g2d, p, q, c(), cutP, cutQ);
    }

    //TBD
    public void drawPointed(ScreenModel<T> screen, Graphics2D g2d) {
        SegDrawer.drawPointed(screen, g2d, p, q);
    }
}
