/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.draw.MVDrawer;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class MV<T extends F<T>> extends AbstractSeg<T> {
    private boolean isMountain = false;

    @Deprecated
    public MV() {
        super();
    }

    public MV(Ver<T> p, Ver<T> q) {
        super(p, q);
    }

    public Crease<T> unfold() {
        return new Crease<T>(p, q);
    }

    @Deprecated
    public boolean isMountain() {
        return isMountain;
    }

    @Deprecated
    public void setMountain(boolean isMountain) {
        this.isMountain = isMountain;
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(S screen, Graphics2D g2d,
            float scale, boolean isPointed) {
        MVDrawer.draw(screen, g2d, scale, p, q, isMountain, isPointed);
    }

    //TODO
    @Override
    public <S extends Graphic<T>> S find(Finder<T, S> finder, double x,
            double y, double scale) {
        return null;
    }
}
