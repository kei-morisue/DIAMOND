/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.io.Serializable;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.LinkDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Link<T extends F<T>> implements Serializable {
    private Ver<T> p;
    private Ver<T> q;
    private Face<T> f;
    private Face<T> g;

    @Deprecated
    public Link() {
    }

    public Link(Face<T> f, Face<T> g, Ver<T> p, Ver<T> q) {
        this.p = p;
        this.q = q;
        this.f = f;
        this.g = g;
    }

    public void draw(ScreenModel screen, Graphics2D g2d) {
        LinkDrawer.draw(screen, g2d, p, q);
    }

    @Deprecated
    public Ver<T> getP() {
        return p;
    }

    @Deprecated
    public void setP(Ver<T> p) {
        this.p = p;
    }

    @Deprecated
    public Ver<T> getQ() {
        return q;
    }

    @Deprecated
    public void setQ(Ver<T> q) {
        this.q = q;
    }

    @Deprecated
    public Face<T> getF() {
        return f;
    }

    @Deprecated
    public void setF(Face<T> f) {
        this.f = f;
    }

    @Deprecated
    public Face<T> getG() {
        return g;
    }

    @Deprecated
    public void setG(Face<T> g) {
        this.g = g;
    }
}
